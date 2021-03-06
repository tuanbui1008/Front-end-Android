package com.btl.bookingroom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.btl.bookingroom.control.AdapterHotel;
import com.btl.bookingroom.control.SlidePagerAdapter;
import com.btl.bookingroom.model.ItemSlide;
import com.btl.bookingroom.model.dbo.Area;
import com.btl.bookingroom.model.dbo.Customer;
import com.btl.bookingroom.model.dbo.Hotel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.HwAds;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import com.huawei.hms.ads.banner.BannerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerActivity extends AppCompatActivity {

    private static final int MESSAGE_GET_INFO_CUSTOMER = 1;
    private static final int MESSAGE_GET_INFO_AREA = 2;
    private static final int MESSAGE_GET_INFO_CATEGORY_ROOM = 3;
    private static final int MESSAGE_GET_DATA_HOTEL = 4;
    private static final int MESSAGE_SELECT_HOTEL_BY_AREA = 5;
    private static final String TAG = "Message";


    private DrawerLayout drawerLayout;
    private Toolbar nav_bar;
    private ActionBarDrawerToggle toggle;
    private List<ItemSlide> itemSlides;
    private ViewPager slide_page;
    private TabLayout tab_slide;
    private NavigationView nav_page;
    private CircleImageView avatar_customer;
    private TextView name_customer, gmail_customer;
    private Customer customer;
    private Handler handler;
    private String gmail;
    private Spinner spinnerArea;
    private List<Area> areaList;
    private List<Hotel> hotelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        HwAds.init(this);
        init();
        initHandler();
        getAllArea(Constant.URL_SELECT_ALL_AREA);
        getAllDataHotel(Constant.URL_SELECT_ALL_DATA_HOTEL);
        customPagerAdapter();
        eventNav();

    }

    private void init() {
        bannerView = findViewById(R.id.banner_view);
        drawerLayout = findViewById(R.id.layout_drawer);
        nav_bar = findViewById(R.id.nav_bar);
        slide_page = findViewById(R.id.slide_page);
        tab_slide = findViewById(R.id.tab_slide);
        nav_page = findViewById(R.id.nav_page);
        spinnerArea = findViewById(R.id.spinner_area);
        Intent intent = getIntent();
        gmail = intent.getStringExtra(Constant.KEY.KEY_GMAIL);
        itemSlides = new ArrayList<>();
        itemSlides.add(new ItemSlide(R.drawable.item_slide_page1));
        itemSlides.add(new ItemSlide(R.drawable.item_slide_page2));
        itemSlides.add(new ItemSlide(R.drawable.item_slide_page3));
        itemSlides.add(new ItemSlide(R.drawable.item_slide_page4));
        setSupportActionBar(nav_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(CustomerActivity.this, drawerLayout, nav_bar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configBanner(){
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);

    }

    private void initHandler() {
        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_GET_INFO_CUSTOMER: {
                        customer = (Customer) msg.obj;
                        headerNav(customer);
                        break;
                    }
                    case MESSAGE_GET_INFO_AREA: {
                        areaList = new ArrayList<>();
                        areaList.addAll((Collection<? extends Area>) msg.obj);
                        List<String> listNameArea = new ArrayList<>();
                        areaList.stream().forEach(area -> {
                            listNameArea.add(area.getName());
                        });
                        customSpinnerArea(listNameArea);
                        break;
                    }
                    case MESSAGE_GET_DATA_HOTEL: {
                        hotelList.clear();
                        hotelList.addAll((Collection<? extends Hotel>) msg.obj);
                        customerAdapterHotel(hotelList);
                        break;
                    }
                    case MESSAGE_SELECT_HOTEL_BY_AREA: {
                        List<Hotel> hotelList = new ArrayList<>();
                        hotelList.addAll((Collection<? extends Hotel>) msg.obj);
                        customerAdapterHotel(hotelList);
                        break;
                    }
                }
            }
        };
    }

    private void customerAdapterHotel(List<Hotel> list) {
        recyclerView = findViewById(R.id.list_hotel);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayout.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AdapterHotel adapterHotel = new AdapterHotel(this, list);
        recyclerView.setAdapter(adapterHotel);
    }


    private void customSpinnerArea(List<String> list) {
        list.add(0, "Ch???n khu v???c");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(arrayAdapter);
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1: {
                        getAllHotelByArea(Constant.ID_HANOI);
                        break;
                    }
                    case 2: {
                        getAllHotelByArea(Constant.ID_DA_NANG);
                        break;
                    }
                    case 3: {
                        getAllHotelByArea(Constant.ID_QUANG_NINH);
                        break;
                    }
                    case 4: {
                        getAllHotelByArea(Constant.ID_TP_HCM);
                        break;
                    }
                    case 5: {
                        getAllHotelByArea(Constant.ID_VUNG_TAU);
                        break;
                    }
                    case 6: {
                        getAllHotelByArea(Constant.ID_NHA_TRANG);
                        break;
                    }
                    case 7: {
                        getAllHotelByArea(Constant.ID_CAT_BA);
                        break;
                    }
                    case 8: {
                        getAllHotelByArea(Constant.ID_QUY_NHON);
                        break;
                    }
                    case 9: {
                        getAllHotelByArea(Constant.ID_PHU_QUOC);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getAllHotelByArea(int idArea) {
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                List<Hotel> list = new ArrayList<>();
                hotelList.stream()
                        .filter(hotel -> hotel.getIdArea() == idArea)
                        .forEach(hotel -> {
                            list.add(hotel);
                        });
                Message msg = new Message();
                msg.what = MESSAGE_SELECT_HOTEL_BY_AREA;
                msg.obj = list;
                handler.sendMessage(msg);
            }
        });
        thread.start();
    }

    public void customPagerAdapter() {
        SlidePagerAdapter pagerAdapter = new SlidePagerAdapter(getApplicationContext(), itemSlides);
        slide_page.setAdapter(pagerAdapter);
        tab_slide.setupWithViewPager(slide_page, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CustomerActivity.RunTimeSlide(), 4000, 6000);
    }

    class RunTimeSlide extends TimerTask {
        @Override
        public void run() {
            CustomerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slide_page.getCurrentItem() < itemSlides.size() - 1) {
                        slide_page.setCurrentItem(slide_page.getCurrentItem() + 1);
                    } else {
                        slide_page.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void headerNav(Customer customer) {
        View headerView = nav_page.getHeaderView(0);
        avatar_customer = headerView.findViewById(R.id.avatar_customer);
        name_customer = headerView.findViewById(R.id.name_customer);
        gmail_customer = headerView.findViewById(R.id.gmail_customer);
        name_customer.setText(customer.getCustomerName());
        gmail_customer.setText(customer.getGmail());
        if (Objects.nonNull(customer.getAvatar()) || customer.getAvatar().isEmpty()) {
            avatar_customer.setImageResource(R.drawable.avatar_default);
        } else {
            Picasso.get().load(customer.getAvatar()).into(avatar_customer);
        }
    }

    private void eventNav() {
        nav_page.bringToFront();
        nav_page.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.log_out: {
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        Toast.makeText(CustomerActivity.this, Constant.MESSAGE.LOGOUT_SUCCESS, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.profile: {
                        Intent intent = new Intent(CustomerActivity.this, InfoCustomerActivity.class);
                        intent.putExtra(Constant.KEY.KEY_CUSTOMER, customer);
                        startActivity(intent);
                        break;
                    }
                    case R.id.changePassword: {
                        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                        intent.putExtra(Constant.KEY.KEY_CUSTOMER, customer);
                        startActivity(intent);
                        break;
                    }
                    case R.id.history:{
                        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        intent.putExtra(Constant.KEY.KEY_CUSTOMER, customer);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void getInfoCustomer(String url) {
        Map<String, String> param = new HashMap<>();
        param.put("gmail", gmail);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(CustomerActivity.this);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(param),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String code = (String) response.get("code");
                                    if (code.equals(Constant.KEY.KEY_CODE_SUCCESS)){
                                        Message msg = new Message();
                                        msg.what = MESSAGE_GET_INFO_CUSTOMER;
                                        msg.obj = convertCustomer(response.getJSONObject(Constant.KEY.KEY_DATA_RES));
                                        handler.sendMessage(msg);
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(CustomerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CustomerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(request);
            }
        });
        thread.start();
    }

    private void getAllArea(String url) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Area> list = new ArrayList<>();
                RequestQueue requestQueue = Volley.newRequestQueue(CustomerActivity.this);
                JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            list.add(new Area(
                                                    jsonObject.getInt("id"),
                                                    jsonObject.getString("name")
                                            ));
                                        } catch (JSONException e) {
                                           Log.e(Constant.TAG, e.getMessage());
                                        }
                                    }
                                    Message msg = new Message();
                                    msg.what = MESSAGE_GET_INFO_AREA;
                                    msg.obj = list;
                                    handler.sendMessage(msg);
                                } catch (JSONException e) {
                                   Log.e(Constant.TAG, e.getMessage());
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CustomerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(jsonArrayRequest);
            }
        });
        thread.start();
    }

    private void getAllDataHotel(String url) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(CustomerActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray(Constant.KEY.KEY_DATA_RES);
                                    List<Hotel> hotels = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            hotels.add(new Hotel(
                                                    jsonObject.getInt("id"),
                                                    jsonObject.getInt("idArea"),
                                                    jsonObject.getString("name"),
                                                    jsonObject.getString("address"),
                                                    jsonObject.getString("image")
                                            ));
                                        } catch (JSONException e) {
                                           Log.e(Constant.TAG, e.getMessage());
                                        }
                                        Message msg = new Message();
                                        msg.what = MESSAGE_GET_DATA_HOTEL;
                                        msg.obj = hotels;
                                        handler.sendMessage(msg);
                                    }
                                } catch (JSONException e) {
                                   Log.e(Constant.TAG, e.getMessage());
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CustomerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(jsonObjectRequest);
            }
        });
        thread.start();
    }

    private Customer convertCustomer(JSONObject object) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        Customer customerRequest = new Customer();
        try {
            customerRequest.setId(Integer.parseInt(object.getString("id")));
            customerRequest.setCustomerName(object.getString("fullName"));
            customerRequest.setPassword(object.getString("passwordHash"));
            customerRequest.setGmail(object.getString("gmail"));
            customerRequest.setAddress(object.getString("address"));
            customerRequest.setGender(object.getString("gender"));
            customerRequest.setAvatar(object.getString("avatar"));
            if (Objects.nonNull(object.get("dob"))) {
                customerRequest.setDOB(format.parse(object.getString("dob")));
            }
        } catch (Exception e) {
            Log.e(Constant.TAG, e.getMessage());
        }
        return customerRequest;
    }

    @Override
    protected void onResume() {
        getInfoCustomer(Constant.URL_SELECT_INFO_CUSTOMER);
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    public void setClickRecyclerView(Hotel hotel) {
        Intent intent = new Intent(this, InfoHotelActivity.class);
        intent.putExtra(Constant.KEY.KEY_HOTEl, hotel);
        intent.putExtra(Constant.KEY.KEY_CUSTOMER, customer);
        startActivity(intent);
    }
}