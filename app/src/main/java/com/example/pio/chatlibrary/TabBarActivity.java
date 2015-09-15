package com.example.pio.chatlibrary;

import android.app.ActionBar;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pio.chatlibrary.chat.User;
import com.example.pio.chatlibrary.fragments.FragmentA;
import com.example.pio.chatlibrary.fragments.FragmentB;
import com.example.pio.chatlibrary.fragments.FragmentC;
import com.example.pio.chatlibrary.network.ActivityListener;
import com.example.pio.chatlibrary.network.FayeHandler;
import com.example.pio.chatlibrary.network.ListenerFaye;
import com.example.pio.chatlibrary.network.RetrofitHandler;
import com.example.pio.chatlibrary.util.Sender;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by pio on 09.09.15.
 */
public class TabBarActivity extends FragmentActivity implements ActionBar.TabListener, ListenerFaye, ActivityListener {

    public static final String TAG = TabBarActivity.class.getSimpleName();

    private ActionBar actionBar;
    private ViewPager viewPager;
    private static String TOKEN;
    private FayeHandler fayeHandler;
    private String userName;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TOKEN = intent.getStringExtra("TOKEN");
        userName = intent.getStringExtra("LOGIN");
        Log.d(TAG, TOKEN);
        setContentView(R.layout.activity_tabviews);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        fayeHandler = new FayeHandler(this);
        setUpTabs();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    private void setUpTabs() {
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabOne = actionBar.newTab();
        tabOne.setText("FragmentA");
        tabOne.setTabListener(this);
        ActionBar.Tab tabTwo = actionBar.newTab();
        tabTwo.setText("FragmentB");
        tabTwo.setTabListener(this);
        ActionBar.Tab tabThree = actionBar.newTab();
        tabThree.setText("FragmentC");
        tabThree.setTabListener(this);

        actionBar.addTab(tabOne);
        actionBar.addTab(tabTwo);
        actionBar.addTab(tabThree);
    }

    @Override
    public void getDataFromChannel(JSONObject jsonObject, String messageType) {

        switch (messageType) {
            case "ALL":
                try {
                    if (!jsonObject.getString("author").equals(userName)) {
                        fragmentA.updateMessagesList(jsonObject.getString("author"),
                                jsonObject.getString("message"), false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "USERS":
                //fragmentC.updateLoggedList(/* */);
                break;
        }
    }

    @Override
    public void getUserMessage(String message) {
        Sender.sendMessage(userName, message, fayeHandler);
    }

    @Override
    public void getPrivateUserMap(HashMap<String, User> privateUserList) {
        fragmentB.updatePrivateList(privateUserList);
    }


    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int i) {


            if (i == 0) {
                fragmentA = new FragmentA();
                return fragmentA;
            } else if (i == 1) {
                fragmentB = new FragmentB();
                return fragmentB;
            } else if (i == 2) {
                fragmentC = new FragmentC();
                return fragmentC;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            actionBar.setSelectedNavigationItem(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            //String authorization = "Basic " + TOKEN;
            String authorization = "Basic " + TOKEN;
            RetrofitHandler retrofit = new RetrofitHandler(getApplicationContext(), getResources().getString(R.string.register));
            retrofit.getLoginRegisterAPI().sign_out(authorization, new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    Log.e(TAG, String.valueOf(response.getStatus()));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, error.toString());
                }
            });
            //Log.e(TAG, String.valueOf(response.getStatus()));
            //Log.e(TAG, error.toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
