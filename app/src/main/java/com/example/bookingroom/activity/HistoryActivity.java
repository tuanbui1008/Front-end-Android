package com.example.bookingroom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookingroom.R;
import com.example.bookingroom.constants.Constant;
import com.example.bookingroom.control.AdapterHistoryBookRoom;
import com.example.bookingroom.control.AdapterHotel;
import com.example.bookingroom.model.dbo.Area;
import com.example.bookingroom.model.dbo.BookRoom;
import com.example.bookingroom.model.dbo.Customer;
import com.example.bookingroom.model.dbo.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private static final int MESSAGE_LOAD_DATA = 1;
    private RecyclerView lstHistory;
    private Handler handler;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        initHandler();
        loadData(Constant.URL_LOAD_HISTORY);
    }

    private void init() {
        lstHistory = findViewById(R.id.lstHistory);
        customer = (Customer) getIntent().getSerializableExtra(Constant.KEY.KEY_CUSTOMER);
    }

    private void initHandler() {
        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_LOAD_DATA: {
                        List<BookRoom> lstData = (List<BookRoom>) msg.obj;
                        customLstHistory(lstData);
                        break;
                    }
                }
            }
        };
    }

    private void customLstHistory(List<BookRoom> lstHis) {
        lstHistory.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayout.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        lstHistory.setLayoutManager(linearLayout);
        lstHistory.addItemDecoration(dividerItemDecoration);
        lstHistory.setItemAnimator(new DefaultItemAnimator());
        AdapterHistoryBookRoom adapter = new AdapterHistoryBookRoom(this, lstHis);
        lstHistory.setAdapter(adapter);
    }

    private void loadData(String url) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        HashMap<String, Object> params = new HashMap<>();
        params.put("idCustomer", customer.getId());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue request = Volley.newRequestQueue(HistoryActivity.this);
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String code = response.getString("code");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                        List<BookRoom> lst = new ArrayList<>();
                                        JSONArray jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            BookRoom room = new BookRoom();
                                            room.setId(jsonArray.getJSONObject(i).getInt("id"));
                                            room.setNameHotel(jsonArray.getJSONObject(i).getString("nameHotel"));
                                            room.setAddress(jsonArray.getJSONObject(i).getString("address"));
                                            room.setCategory(jsonArray.getJSONObject(i).getString("category"));
                                            room.setRoomCode(jsonArray.getJSONObject(i).getString("roomCode"));
                                            room.setAmountPerson(jsonArray.getJSONObject(i).getInt("amountPerson"));
                                            room.setAmountRoom(jsonArray.getJSONObject(i).getInt("amountRoom"));
                                            room.setDateOfArrival(format.parse(jsonArray.getJSONObject(i).getString("dateOfArrival")));
                                            room.setDateGo(format.parse(jsonArray.getJSONObject(i).getString("dateGo")));
                                            room.setCreatedDate(format.parse(jsonArray.getJSONObject(i).getString("createdDate")));
                                            room.setPrice(jsonArray.getJSONObject(i).getDouble("price"));
                                            lst.add(room);
                                        }
                                        if (!lst.isEmpty()){
                                            Message msg = new Message();
                                            msg.what = MESSAGE_LOAD_DATA;
                                            msg.obj = lst;
                                            handler.sendMessage(msg);
                                        }
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(HistoryActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                                } catch (ParseException e) {
                                    Toast.makeText(HistoryActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(HistoryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                request.add(req);
            }
        });
        thread.start();
    }
}