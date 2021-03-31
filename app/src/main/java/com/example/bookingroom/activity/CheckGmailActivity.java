package com.example.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bookingroom.constants.Constant;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckGmailActivity extends AppCompatActivity {
    private EditText ed_code;
    private Button btn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_gmail);
        init();
        event();
    }

    private void init() {
        ed_code = findViewById(R.id.ed_code);
        btn_check = findViewById(R.id.btn_check_gmail);
    }

    public void event() {
        Intent intent = getIntent();
        String customerName = intent.getStringExtra("customername");
        String gmail = intent.getStringExtra("gmail");
        String password = intent.getStringExtra("password");
        String gender = intent.getStringExtra("gender");
        int code_check = intent.getIntExtra("code_check", -1);
        btn_check.setOnClickListener(v -> {
            int code = Integer.parseInt(ed_code.getText().toString());
            if (code == code_check) {
                sendRequest(Constant.URL_CREATE_USER, customerName, gmail, password, gender);
                Toast.makeText(getApplicationContext(), Constant.MESSAGE.CREATED_SUCCESS, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), Constant.MESSAGE.CODE, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendRequest(String url, String customerName, String gmail, String password, String gender) {
        Map<String, String> params = new HashMap<>();
        params.put("fullName", customerName);
        params.put("gmail", gmail);
        params.put("passwordHash", password);
        params.put("gender", gender);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = (String) response.get("code");
                            if (code.equals("success")) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), Constant.MESSAGE.ADD_FAIL, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e(Constant.TAG,e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

        };
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), CreatedUserActivity.class));
        finish();
    }
}