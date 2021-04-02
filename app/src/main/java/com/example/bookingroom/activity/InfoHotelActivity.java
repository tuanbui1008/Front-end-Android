package com.example.bookingroom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.example.bookingroom.control.AdapterComment;
import com.example.bookingroom.control.SlideHotelAdapter;
import com.example.bookingroom.control.SlideTypeRoomAdapter;
import com.example.bookingroom.model.CommentOfCustomer;
import com.example.bookingroom.model.TypeRoom;
import com.example.bookingroom.model.dbo.Customer;
import com.example.bookingroom.model.dbo.Hotel;
import com.example.bookingroom.model.dbo.ImageRoom;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InfoHotelActivity extends AppCompatActivity {
    private static final int MESSAGE_SELECT_IMAGE_HOTEL = 1;
    private static final int MESSAGE_SELECT_TYPE_ROOM = 2;
    private static final int MESSAGE_SELECT_MESSAGE = 3;
    private ViewPager viewPager;
    private RecyclerView recyclerView, lstViewCmt;
    private Handler handler;
    //    private int idHotel;
    private Hotel hotel;
    private Customer customer;
    private TabLayout tabLayout;
    private List<ImageRoom> imageRoomList;
    private List<TypeRoom> typeRoomList;
    private List<CommentOfCustomer> lstCmt;
    private TextView lblNameHotel, lblAddressHotel, ed_cmt;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);
        init();
        initHandler();
        getImageHotel(Constant.URL_SELECT_IMAGE_HOTEL);
        getTypeRoom(Constant.URL_SELECT_TYPE_ROOM);
        loadDataComment(Constant.URL_GET_COMMENT);
        event();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_SELECT_IMAGE_HOTEL: {
                        imageRoomList = new ArrayList<>();
                        imageRoomList.addAll((Collection<? extends ImageRoom>) msg.obj);
                        customImageHotel(imageRoomList);
                        setupInfoHotel(hotel);
                        break;
                    }
                    case MESSAGE_SELECT_TYPE_ROOM: {
                        typeRoomList = new ArrayList<>();
                        typeRoomList.addAll((Collection<? extends TypeRoom>) msg.obj);
                        customTypeRoom(typeRoomList);
                        break;
                    }
                    case MESSAGE_SELECT_MESSAGE: {
                        lstCmt = new ArrayList<>();
                        lstCmt.addAll((Collection<? extends CommentOfCustomer>) msg.obj);
                        customDataComment(lstCmt);
                        break;
                    }
                }
            }
        };

    }

    private void customDataComment(List<CommentOfCustomer> lst) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration decoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        Drawable drawable = getDrawable(R.drawable.custom_divider);
        decoration.setDrawable(drawable);
        lstViewCmt.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        AdapterComment adapter = new AdapterComment(this, lst);
        lstViewCmt.setAdapter(adapter);
        lstViewCmt.setHasFixedSize(true);

    }

    private void setupInfoHotel(Hotel hotel) {
        lblNameHotel.setText(hotel.getName());
        lblAddressHotel.setText(hotel.getAddress());
    }

    private void customTypeRoom(List<TypeRoom> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration decoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        Drawable drawable = getDrawable(R.drawable.custom_divider);
        decoration.setDrawable(drawable);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        SlideTypeRoomAdapter adapter = new SlideTypeRoomAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }


    private void customImageHotel(List<ImageRoom> list) {
        SlideHotelAdapter adapter = new SlideHotelAdapter(getApplicationContext(), list);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        lblAddressHotel.setVisibility(View.VISIBLE);
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager_hotel);
        tabLayout = findViewById(R.id.tab);
        recyclerView = findViewById(R.id.list_type_room);
        lstViewCmt = findViewById(R.id.list_cmt);
        lblNameHotel = findViewById(R.id.lbl_name_hotel);
        lblAddressHotel = findViewById(R.id.lbl_address_hotel);
        hotel = (Hotel) getIntent().getSerializableExtra(Constant.KEY.KEY_HOTEl);
        btn_send = findViewById(R.id.btn_send);
        ed_cmt = findViewById(R.id.ed_cmt);
        customer = (Customer) getIntent().getSerializableExtra(Constant.KEY.KEY_CUSTOMER);
    }

    private void event() {
        btn_send.setOnClickListener(v -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sendComment(Constant.URL_ADD_COMMENT);
                }
            });
            thread.start();
        });
    }

    private void sendComment(String url) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(Constant.KEY.KEY_CONTENT, ed_cmt.getText().toString().trim());
        params.put("idCustomer", customer.getId());
        params.put("idHotel", hotel.getIdHotel());
        RequestQueue requestQueue = Volley.newRequestQueue(InfoHotelActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = (String) response.get("code");
                            if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                loadDataComment(Constant.URL_GET_COMMENT);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(InfoHotelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InfoHotelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(request);
    }

    private void loadDataComment(String url) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("idCustomer", customer.getId());
        params.put("idHotel", hotel.getIdHotel());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(InfoHotelActivity.this);
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String code = null;
                                List<CommentOfCustomer> list = new ArrayList<>();
                                try {
                                    code = (String) response.get("code");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                        JSONArray jsonArray = (JSONArray) response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            String image = convertStringNull(jsonArray.getJSONObject(i).getString("image"));
                                            list.add(new CommentOfCustomer(
                                                    jsonArray.getJSONObject(i).getInt("id"),
                                                    jsonArray.getJSONObject(i).getString("fullName"),
                                                    image,
                                                    jsonArray.getJSONObject(i).getString("content")
                                            ));
                                            Message msg = new Message();
                                            msg.what = MESSAGE_SELECT_MESSAGE;
                                            msg.obj = list;
                                            handler.sendMessage(msg);
                                        }
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(InfoHotelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(InfoHotelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(req);
            }
        });
        thread.start();
    }

    private void getImageHotel(String murl) {
        Map<String, String> param = new HashMap<>();
        param.put("idHotel", String.valueOf(hotel.getIdHotel()));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(InfoHotelActivity.this);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, murl, new JSONObject(param),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                List<ImageRoom> list = new ArrayList<>();
                                try {
                                    String code = (String) response.get("code");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                        JSONArray jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            list.add(new ImageRoom(
                                                    jsonArray.getJSONObject(i).getString("image")
                                            ));
                                            Message msg = new Message();
                                            msg.what = MESSAGE_SELECT_IMAGE_HOTEL;
                                            msg.obj = list;
                                            handler.sendMessage(msg);
                                        }
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(InfoHotelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(InfoHotelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(request);
            }
        });
        thread.start();
    }

    private void getTypeRoom(String murl) {
        Map<String, String> param = new HashMap<>();
        param.put("idHotel", String.valueOf(hotel.getIdHotel()));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, murl, new JSONObject(param),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String code = (String) response.get("code");
//                                    String message = (String) response.get("message");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)) {
                                        List<TypeRoom> list = new ArrayList<>();
                                        try {
                                            JSONArray jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                list.add(new TypeRoom(
                                                        jsonArray.getJSONObject(i).getInt("id"),
                                                        jsonArray.getJSONObject(i).getString("name"),
                                                        jsonArray.getJSONObject(i).getInt("price")
                                                ));
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(InfoHotelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                        Message msg = new Message();
                                        msg.what = MESSAGE_SELECT_TYPE_ROOM;
                                        msg.obj = list;
                                        handler.sendMessage(msg);
                                    } else {
                                        Toast.makeText(InfoHotelActivity.this, code, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(InfoHotelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(InfoHotelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(request);
            }
        });
        thread.start();
    }

    public void setClickTypeRoom(TypeRoom typeRoom) {
        Intent intent = new Intent(getApplicationContext(), BookRoomActivity.class);
        intent.putExtra(Constant.KEY.KEY_TYPE_ROOM, typeRoom);
        intent.putExtra(Constant.KEY.KEY_CUSTOMER, customer);
        intent.putExtra(Constant.KEY.KEY_HOTEl, hotel);
        startActivity(intent);
    }

    private String convertStringNull(String str) {
        if (Objects.isNull(str)) {
            return "";
        }
        return str;
    }

}