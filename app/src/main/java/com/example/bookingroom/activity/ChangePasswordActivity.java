package com.example.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookingroom.R;
import com.example.bookingroom.constants.Constant;
import com.example.bookingroom.model.dbo.Customer;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextInputLayout layout_psw_old, layout_psw_new, layout_psw_confirm;
    private TextView lblCurrentPas;
    private Button btn_change;
    private ProgressDialog progressDialog;
    private Customer customer;
    private String gmail;
    private boolean keyChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getDataIntent();
        init();
        initDialog();
        event();
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(Constant.Title.LOADING_CHANGE);
        progressDialog.setMessage(Constant.Title.LOADING);
    }

    private void event() {
        btn_change.setOnClickListener(v -> {
            changePasswordNew();
        });

    }

    private void changePasswordNew() {

        if (keyChange) {
            if (checkPasswordConfirm()){
                progressDialog.show();
                sendRequest(Constant.URL_UPDATE_INFO_CUSTOMER);
            }
        }else {
            if (checkPasswordOdl() && checkPasswordConfirm()) {
                progressDialog.show();
                sendRequest(Constant.URL_UPDATE_INFO_CUSTOMER);
            }
        }
    }

    private void sendRequest(String url) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        Map<String, Object> params = new HashMap<>();
        params.put("gmail", customer.getGmail());
        params.put("id", String.valueOf(customer.getId()));
        params.put("fullName", customer.getCustomerName());
        params.put("gender", customer.getGender());
        params.put("address", customer.getAddress());
        params.put("dob", format.format(customer.getDOB()));
        params.put("avatar", "");
        params.put("passwordHash", layout_psw_confirm.getEditText().getText().toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = (String) response.get("code");
                            if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                finish();
                                if (keyChange){
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                }
                                Toast.makeText(ChangePasswordActivity.this, Constant.MESSAGE.CHANGE_PASSWORD_SUCCESS, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, Constant.MESSAGE.CHANGE_PASSWORD_ERROR, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e(Constant.TAG, e.getMessage());
                        } finally {
                            progressDialog.dismiss();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ChangePasswordActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(request);
    }

    private boolean checkPasswordNew() {
        String ed_psw_new = layout_psw_new.getEditText().getText().toString();
        Matcher matcher = Pattern.compile(Constant.PATTERN_PASSWORD).matcher(ed_psw_new);
        if (matcher.matches()) {
            layout_psw_new.setError(null);
            return true;
        } else {
            layout_psw_new.setError(Constant.MESSAGE.CHECK_PASSWORD);
            return false;
        }
    }

    private boolean checkPasswordConfirm() {
        String ed_psw_confirm = layout_psw_confirm.getEditText().getText().toString();
        String ed_psw_new = layout_psw_new.getEditText().getText().toString();
        if (checkPasswordNew()) {
            if (ed_psw_confirm.equals(ed_psw_new)) {
                layout_psw_confirm.setError(null);
                return true;
            } else {
                layout_psw_confirm.setError(Constant.MESSAGE.CONFIRM_PASSWORD);
                return false;
            }
        }
        return false;
    }

    private boolean checkPasswordOdl() {
        String ed_password = layout_psw_old.getEditText().getText().toString();
        if (customer.getPassword().equals(ed_password)) {
            layout_psw_old.setError(null);
            return true;
        } else {
            layout_psw_old.setError(Constant.MESSAGE.ERROR_PASSWORD);
            return false;
        }
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        keyChange = intent.getBooleanExtra(Constant.KEY.KEY_CONFIRM_NEW_PASSWORD,false);
        if (keyChange){

            gmail = intent.getStringExtra(Constant.KEY.KEY_GMAIL);
            getCustomerByGmail(gmail);
        }else {
            customer = (Customer) intent.getSerializableExtra(Constant.KEY.KEY_CUSTOMER);
        }
    }

    private void getCustomerByGmail(String gmail){
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> params = new HashMap<>();
                params.put("gmail", gmail);
                RequestQueue request = Volley.newRequestQueue(ChangePasswordActivity.this);
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Constant.URL_SELECT_INFO_CUSTOMER, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject res) {
                                try {
                                    String code = res.getString("code");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)){
                                        JSONObject object = res.getJSONObject("data");
                                        customer = new Customer();
                                        customer.setId(object.getInt("id"));
                                        customer.setCustomerName(object.getString("fullName"));
                                        customer.setGmail(object.getString("gmail"));
                                        customer.setAddress(object.getString("address"));
                                        customer.setGender(object.getString("gender"));
                                        customer.setAvatar(object.getString("avatar"));
                                        if (Objects.nonNull(object.get("dob"))) {
                                            customer.setDOB(format.parse(object.getString("dob")));
                                        }
                                    }
                                    else {
                                        Toast.makeText(ChangePasswordActivity.this,Constant.MESSAGE.NOT_FIND,Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                } catch (ParseException e) {
                                    Toast.makeText(ChangePasswordActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ChangePasswordActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                request.add(req);
            }
        });
        thread.start();
    }
    private void init() {
        layout_psw_old = findViewById(R.id.layout_psw_old);
        layout_psw_new = findViewById(R.id.layout_psw_new);
        layout_psw_confirm = findViewById(R.id.layout_psw_confirm);
        btn_change = findViewById(R.id.btn_change);
        lblCurrentPas = findViewById(R.id.lblCurrentPas);
        if (keyChange){
            lblCurrentPas.setVisibility(View.GONE);
            layout_psw_old.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}