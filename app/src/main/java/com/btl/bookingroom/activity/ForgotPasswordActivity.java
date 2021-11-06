package com.btl.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.btl.bookingroom.R;
import com.btl.bookingroom.apiSendEmail.SendGmail;
import com.btl.bookingroom.constants.Constant;
import com.btl.bookingroom.constants.RandomCode;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputLayout layoutGmail;
    private Button btnForgot;
    private ProgressDialog progressDialog;
    private String nameGmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
        initDialog();
        event();
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(Constant.Title.CHECK);
        progressDialog.setMessage(Constant.Title.LOADING);
    }


    private void event() {
        btnForgot.setOnClickListener(v -> {
            if (checkGmail()) {
                progressDialog.show();
                requestCheckGmail(Constant.URL_CHECK_EXIST_GMAIL);
            }
        });
    }

    private boolean checkGmail() {
        String gmail = layoutGmail.getEditText().getText().toString();
        Matcher matcher = Pattern.compile(Constant.PATTERN_EMAIL).matcher(gmail);
        if (!matcher.matches()) {
            layoutGmail.setError(Constant.MESSAGE.CHECK_EMAIL);
            return false;
        } else {
            layoutGmail.setError(null);
            return true;
        }
    }

    private void init() {
        layoutGmail = findViewById(R.id.layout_gmail);
        btnForgot = findViewById(R.id.btn_forgot);
    }

    private void requestCheckGmail(String url) {
        Map<String, String> param = new HashMap<>();
        param.put("gmail", layoutGmail.getEditText().getText().toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(param),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject res) {
                        try {
                            String code = res.getString("code");
                            if (code.equals(Constant.KEY.KEY_CODE_CONFIRM)){
                                int codeAccept = new RandomCode().randomCode(100000, 999999);
                                new SendGmail().send(String.valueOf(codeAccept), layoutGmail.getEditText().getText().toString());
                                Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmForgotActivity.class);
                                intent.putExtra(Constant.KEY.KEY_CODE_ACCEPT, codeAccept);
                                intent.putExtra(Constant.KEY.KEY_GMAIL, layoutGmail.getEditText().getText().toString());
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(ForgotPasswordActivity.this, Constant.MESSAGE.EXIST_EMAIL, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            progressDialog.dismiss();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotPasswordActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(req);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}