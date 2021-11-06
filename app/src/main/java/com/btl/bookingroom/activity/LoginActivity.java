package com.btl.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.btl.bookingroom.R;
import com.btl.bookingroom.constants.Constant;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText ed_email, ed_psw;
    private CheckBox cb_remember;
    private TextView tv_forgot, tv_create_user;
    private Button btn_login;
    private TextInputLayout layout_email, layout_psw;
    private SharedPreferences sharedPreferences;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setInfoAccount();
        event();
    }

    public void init() {
        ed_email = findViewById(R.id.ed_email);
        ed_psw = findViewById(R.id.ed_psw);
        cb_remember = findViewById(R.id.cb_remember);
        tv_forgot = findViewById(R.id.lbl_forgot_psw);
        tv_forgot.setPaintFlags(R.color.colorBlue);
        tv_create_user = findViewById(R.id.lbl_create_user);
        btn_login = findViewById(R.id.btn_login);
        layout_email = findViewById(R.id.layout_email);
        layout_psw = findViewById(R.id.layout_psw);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setContentView(R.layout.progress_dilog);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(Constant.Title.LOGIN);
        progressDialog.setMessage(Constant.Title.LOADING);
//        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        sharedPreferences = getSharedPreferences(Constant.KEY.KEY_ACCOUNT, MODE_PRIVATE);
    }

    private void setInfoAccount() {
        ed_email.setText(sharedPreferences.getString(Constant.KEY.KEY_GMAIL, ""));
        ed_psw.setText(sharedPreferences.getString(Constant.KEY.KEY_PASSWORD, ""));
        cb_remember.setChecked(sharedPreferences.getBoolean(Constant.KEY.KEY_REMEMBER, false));
    }

    public void event() {
        btn_login.setOnClickListener(v -> {
            eve_login();
        });
        tv_create_user.setOnClickListener(v -> {
            eve_create_user();
        });
        tv_forgot.setOnClickListener(v -> {
            even_forgot_psw();
        });
    }

    private void eve_create_user() {
        finish();
        startActivity(new Intent(getApplicationContext(), CreatedUserActivity.class));
    }

    private void eve_login() {
        setRemember();
        if (checkEmail() && checkPassword()) {
            progressDialog.show();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    requestLogin(Constant.URL_LOGIN);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), Constant.MESSAGE.CHECK_ACCOUNT, Toast.LENGTH_SHORT).show();
        }
    }

    private void requestLogin(String url) {
        Map<String, String> params = new HashMap<>();
        params.put("gmail", layout_email.getEditText().getText().toString().trim());
        params.put("passwordHash", layout_psw.getEditText().getText().toString().trim());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code = null;
                        try {
                            code = (String) response.get("code");
                            if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                                intent.putExtra(Constant.KEY.KEY_GMAIL, layout_email.getEditText().getText().toString());
                                progressDialog.dismiss();
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, Constant.MESSAGE.CHECK_ACCOUNT, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void even_forgot_psw() {
        finish();
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }

    private boolean checkEmail() {
        String textEmail = layout_email.getEditText().getText().toString().trim();
        Matcher matcher = Pattern.compile(Constant.PATTERN_EMAIL).matcher(textEmail);
        if (!matcher.matches()) {
            layout_email.setError(Constant.MESSAGE.CHECK_EMAIL);
            return false;
        } else {
            layout_email.setError(null);
            return true;
        }
    }

    private boolean checkPassword() {
        String textPassword = layout_psw.getEditText().getText().toString().trim();
        Matcher matcher = Pattern.compile(Constant.PATTERN_PASSWORD).matcher(textPassword);
        if (!matcher.matches()) {
            layout_psw.setError(Constant.MESSAGE.CHECK_PASSWORD);
            return false;
        } else {
            layout_psw.setError(null);
            return true;
        }
    }

    private void setRemember() {
        if (cb_remember.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constant.KEY.KEY_GMAIL, ed_email.getText().toString());
            editor.putString(Constant.KEY.KEY_PASSWORD, ed_psw.getText().toString());
            editor.putBoolean(Constant.KEY.KEY_REMEMBER, true);
            editor.commit();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(Constant.KEY.KEY_GMAIL);
            editor.remove(Constant.KEY.KEY_PASSWORD);
            editor.remove(Constant.KEY.KEY_REMEMBER);
            editor.commit();
        }
    }
}