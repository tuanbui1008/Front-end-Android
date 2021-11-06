package com.btl.bookingroom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
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
import com.btl.bookingroom.model.TypeRoom;
import com.btl.bookingroom.model.dbo.Customer;
import com.btl.bookingroom.model.dbo.Hotel;
import com.btl.bookingroom.model.dbo.Room;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookRoomActivity extends AppCompatActivity {

    private static final int MESSAGE = 1;

    private TextView lblNameHotel;
    private TextView lblAddressHotel;
    private TextView price;
    private TextInputEditText dateOfArrival;
    private TextInputEditText dateGo;
    private TextInputEditText amountPerson;
    private TextInputEditText amountRoom;
    private TypeRoom typeRoom;
    private Customer customer;
    private Handler handler;
    private Hotel hotel;
    private Spinner lstRoom;
    private Button btnBookRoom;
    private ProgressDialog progressDialog;
    private List<Room> lstRoomRes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);
        init();
        initHandler();
        loadDataRoom();
        event();
    }

    private void event() {
        btnBookRoom.setOnClickListener(v -> {
            progressDialog.show();
            eventBookRoom(Constant.URL_BOOK_ROOM);
        });
    }


    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE: {
                        List<Room> lst = (List<Room>) msg.obj;
                        customSpinner(lst);
                        break;
                    }
                }
            }
        };
    }

    private void customSpinner(List<Room> lst) {
        List<String> lstCodeRoom = new ArrayList<>();
        lstCodeRoom.add(0, Constant.Title.Chose);
        lst.stream().forEach(item -> {
            lstCodeRoom.add(item.getRoomCode());
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lstCodeRoom);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstRoom.setAdapter(arrayAdapter);
        Room room = lst.stream()
                .filter(item -> item.getRoomCode().equals(lstRoom.getSelectedItem().toString()))
                .findFirst().orElse(null);
    }

    private void init() {
        price = findViewById(R.id.priceRoom);
        lblNameHotel = findViewById(R.id.lblNameHotel);
        lblAddressHotel = findViewById(R.id.lblAddressHotel);
        progressDialog = new ProgressDialog(BookRoomActivity.this);
        progressDialog.setContentView(R.layout.progress_dilog);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(Constant.Title.BOOK_ROOM);
        progressDialog.setMessage(Constant.Title.LOADING);
        typeRoom = (TypeRoom) getIntent().getSerializableExtra(Constant.KEY.KEY_TYPE_ROOM);
        customer = (Customer) getIntent().getSerializableExtra(Constant.KEY.KEY_CUSTOMER);
        hotel = (Hotel) getIntent().getSerializableExtra(Constant.KEY.KEY_HOTEl);
        amountPerson = findViewById(R.id.amountPerson);
        amountRoom = findViewById(R.id.amountRoom);
        dateOfArrival = findViewById(R.id.dateOfArrival);
        dateGo = findViewById(R.id.dateGo);
        lstRoom = findViewById(R.id.lstRoom);
        btnBookRoom = findViewById(R.id.btnBookRoom);
        dateOfArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int yearNow = cal.get(Calendar.YEAR);
                int monthNow = cal.get(Calendar.MONTH);
                int dayNow = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog pickerDialog = new DatePickerDialog(BookRoomActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            cal.set(year, month, dayOfMonth);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
                            dateOfArrival.setText(format.format(cal.getTime()));
                        }, yearNow, monthNow, dayNow);
                pickerDialog.show();
            }
        });

        dateGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int yearNow = cal.get(Calendar.YEAR);
                int monthNow = cal.get(Calendar.MONTH);
                int dayNow = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog pickerDialog = new DatePickerDialog(BookRoomActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            cal.set(year, month, dayOfMonth);
                            SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
                            dateGo.setText(format.format(cal.getTime()));
                        }, yearNow, monthNow, dayNow);
                pickerDialog.show();
            }
        });
        setTitle();
    }

    private void setTitle() {
        lblNameHotel.setText(hotel.getName());
        lblAddressHotel.setText(hotel.getAddress());
        price.setText(String.valueOf(typeRoom.getPrice()));
    }

    private Room getRoom() {
        return lstRoomRes.stream()
                .filter(item -> item.getRoomCode().equals(lstRoom.getSelectedItem().toString()))
                .findFirst().orElse(null);
    }

    private void eventBookRoom(String url) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        Room room = getRoom();
        HashMap<String, Object> params = new HashMap<>();
        params.put("idCustomer", customer.getId());
        params.put("idRoom", room.getId());
        params.put("createdDate", format.format(new Date()));
        params.put("dateOfArrival", dateOfArrival.getText().toString());
        params.put("dateGo", dateGo.getText().toString());
        params.put("amountPerson", amountPerson.getText().toString());
        params.put("amountRoom", amountRoom.getText().toString());
        params.put("price", price.getText().toString());

        RequestQueue request = Volley.newRequestQueue(BookRoomActivity.this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                finish();
                                Toast.makeText(BookRoomActivity.this, Constant.MESSAGE.BOOK_SUCCESS, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(BookRoomActivity.this, Constant.MESSAGE.BOOK_FAIL, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(BookRoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } finally {
                            progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(BookRoomActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        request.add(req);
    }

    private void loadDataRoom() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("idCategory", typeRoom.getId());
        params.put("idHotel", hotel.getIdHotel());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue request = Volley.newRequestQueue(BookRoomActivity.this);
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Constant.URL_GET_LST_ROOM, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String code = response.getString("code");
                                    List<Room> lst = new ArrayList<>();
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                        JSONArray jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            Room room = new Room();
                                            room.setId(jsonArray.getJSONObject(i).getInt("id"));
                                            room.setRoomCode(jsonArray.getJSONObject(i).getString("roomCode"));
                                            room.setAmount_max(jsonArray.getJSONObject(i).getInt("amountMax"));
                                            room.setPrice(jsonArray.getJSONObject(i).getDouble("price"));
                                            lst.add(room);
                                        }
                                        lstRoomRes = lst;
                                        if (!lst.isEmpty()) {
                                            Message msg = new Message();
                                            msg.what = MESSAGE;
                                            msg.obj = lst;
                                            handler.sendMessage(msg);
                                        }
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(BookRoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookRoomActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                request.add(req);
            }
        });
        thread.start();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}