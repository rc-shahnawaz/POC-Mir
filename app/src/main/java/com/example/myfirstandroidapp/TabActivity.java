package com.example.myfirstandroidapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.example.myfirstandroidapp.model.APIResponse;
import com.example.myfirstandroidapp.model.OrderHistory;
import com.example.myfirstandroidapp.retrofit.API;
import com.google.android.material.tabs.TabLayout;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TabActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    //This is our tablayout
    private TabLayout tabLayout;
    private API api;
    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_screen);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev-mf-marketing-experience-api.ms.sandbox.mfrm.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        api = retrofit.create(API.class);

        //Adding toolbar to the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = findViewById(R.id.tabs);


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Customer"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Cases"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = findViewById(R.id.view_pager);

        final String customerID = getIntent().getStringExtra(MainActivity.getEXTRA_CUSTOMER_ID());
        String firstName = getIntent().getStringExtra(MainActivity.getEXTRA_FIRST_NAME());
        String lastName = getIntent().getStringExtra(MainActivity.getEXTRA_LAST_NAME());
        String zipCode = getIntent().getStringExtra(MainActivity.getEXTRA_ZIP_CODE());

        final HashMap map = new HashMap();

        final ProgressDialog dialog = ProgressDialog.show(this, null, "Loading...");


        api.submit(customerID, firstName, zipCode, lastName)
                .flatMap(new Function<List<APIResponse>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<APIResponse> apiResponses) throws Exception {
                        map.put(0, apiResponses);

                        return api.orders(customerID).flatMap(new Function<List<OrderHistory>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(List<OrderHistory> orderHistories) throws Exception {
                                map.put(1, orderHistories);
                                map.put(2, new ArrayList<>());
                                //Creating our pager adapter
                                PagerAdapter adapter = new PagerAdapter(TabActivity.this, getSupportFragmentManager(), tabLayout.getTabCount(), map);

                                //Adding adapter to pager
                                viewPager.setAdapter(adapter);

                                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                                dialog.dismiss();
                                return null;
                            }
                        });
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        new AlertDialog.Builder(TabActivity.this)
                                .setTitle("Error")
                                .setMessage("An error occurred")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                    }
                });


        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}