package com.example.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookingroom.R;
import com.example.bookingroom.apiSendEmail.SendGmail;
import com.example.bookingroom.constants.Constant;
import com.example.bookingroom.constants.RandomCode;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatedUserActivity extends AppCompatActivity {
    private static final int MAX = 999999;
    private static final int MIN = 100000;
    private TextInputLayout layout_customer, layout_gmail, layout_password;
    private RadioButton rd_male, rd_female;
    private Button btn_register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_user);
        init();
        initDialog();
        event();
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Vui lòng đợi...");
    }

    private void init() {
        layout_customer = findViewById(R.id.layout_customer);
        layout_gmail = findViewById(R.id.layout_gmail);
        layout_password = findViewById(R.id.layout_psw);
        btn_register = findViewById(R.id.btn_register);
        rd_male = findViewById(R.id.male);
        rd_female = findViewById(R.id.female);
    }

    private void event() {
        btn_register.setOnClickListener(v -> {
            event_btn_register();
        });
    }


    private void event_btn_register() {

        if (check_cus() && check_email() && checkPassword()) {
            progressDialog.show();
            checkRequestGmail();
        } else {
            Toast.makeText(getApplicationContext(), Constant.MESSAGE.CHECK_INFO, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPassword() {
        String text_password = layout_password.getEditText().getText().toString().trim();
        Matcher matcher_password = Pattern.compile(Constant.PATTERN_PASSWORD).matcher(text_password);
        if (!matcher_password.matches()) {
            layout_password.setError(Constant.MESSAGE.CHECK_PASSWORD);
            return false;
        } else {
            layout_password.setError(null);
            return true;
        }
    }

    private boolean check_cus() {
        String text_customer = layout_customer.getEditText().getText().toString().trim();
        if (text_customer.isEmpty()) {
            layout_customer.setError(Constant.MESSAGE.CHECK_USERNAME);
            return false;
        } else {
            layout_customer.setError(null);
            return true;
        }
    }

    private boolean check_email() {
        String text_email = layout_gmail.getEditText().getText().toString().trim();
        Matcher matcher_email = Pattern.compile(Constant.PATTERN_EMAIL).matcher(text_email);
        if (!matcher_email.matches()) {
            layout_gmail.setError(Constant.MESSAGE.CHECK_EMAIL);
            return false;
        } else {
            layout_gmail.setError(null);
            return true;
        }
    }

    private void checkRequestGmail() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> param = new HashMap<>();
        param.put("gmail", layout_gmail.getEditText().getText().toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                Constant.URL_CHECK_EXIST_GMAIL, new JSONObject(param),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = (String) response.get("code");
                            if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                progressDialog.dismiss();
                                layout_gmail.setError(null);
                                int codeAccept = new RandomCode().randomCode(MIN, MAX);
                                new SendGmail().send(String.valueOf(codeAccept), layout_gmail.getEditText().getText().toString());
                                Intent intent = new Intent(getApplicationContext(), CheckGmailActivity.class);
                                intent.putExtra("customername", layout_customer.getEditText().getText().toString().trim());
                                intent.putExtra("gmail", layout_gmail.getEditText().getText().toString().trim());
                                intent.putExtra("password", layout_password.getEditText().getText().toString().trim());
                                if (rd_male.isChecked()) {
                                    intent.putExtra("gender", Constant.KEY.KEY_MALE);
                                } else if (rd_female.isChecked()) {
                                    intent.putExtra("gender", Constant.KEY.KEY_FEMALE);
                                }
                                intent.putExtra("code_check", codeAccept);
                                startActivity(intent);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                layout_gmail.setError(Constant.MESSAGE.EXIST_EMAIL);
                            }
                        } catch (JSONException e) {
                            Log.e(Constant.TAG, e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(CreatedUserActivity.this, error.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private String spiltString(String string) {
        String[] arrString = string.split("\\@");
        return arrString[0];
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}