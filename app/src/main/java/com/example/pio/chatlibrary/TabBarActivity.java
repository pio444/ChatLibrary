package com.example.pio.chatlibrary;

import android.app.ActionBar;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.pio.chatlibrary.chat.User;
import com.example.pio.chatlibrary.fragments.FragmentA;
import com.example.pio.chatlibrary.fragments.FragmentB;
import com.example.pio.chatlibrary.fragments.FragmentC;
import com.example.pio.chatlibrary.fragments.WrongDialog;
import com.example.pio.chatlibrary.login.LoginActivity;
import com.example.pio.chatlibrary.network.ActivityListener;
import com.example.pio.chatlibrary.network.ChatPusher;
import com.example.pio.chatlibrary.network.FayeClient;
import com.example.pio.chatlibrary.network.Retrofit;
import com.example.pio.chatlibrary.util.Network;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pio on 09.09.15.
 */
public class TabBarActivity extends FragmentActivity implements ActionBar.TabListener,
                                                        Handler.Callback,
                                                        FragmentA.SendMessage,
                                                        ActivityListener,
                                                        WrongDialog.SendAgain,
                                                        FragmentB.PrivetListener {

    public static final String TAG = TabBarActivity.class.getSimpleName();

    private MyApplication myApplication ;
    private ActionBar actionBar;
    private ViewPager viewPager;
    private static String TOKEN;
    private FayeClient fayeClient;
    private FayeClient fayeClient2;
    private FayeClient fayeClient3;
    private List<FayeClient> fayeClientList;
    private static String LOGIN;
    public static final int BACKGROUND_OPERATION = 10;
    public static final int USER_OPERATION = 20;
    public static final int LOGGIN =30;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private Handler mUiHandler;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication)getApplicationContext();
        Intent intent = getIntent();
        TOKEN = intent.getStringExtra("TOKEN");
        Log.d(TAG, TOKEN);
        LOGIN = intent.getStringExtra("LOGIN");
        setContentView(R.layout.activity_tabviews);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        setUpTabs();

        HandlerThread handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper(), this);
        mUiHandler = new Handler(getMainLooper(), this);
        if (Network.isNetworkAvailable(this)) {

            fayeClient = new FayeClient("/all", mHandler, TOKEN,LOGIN);
            fayeClient.start();
            fayeClient2 = new FayeClient("/users", mHandler, TOKEN,LOGIN);
            fayeClient2.start();
            fayeClient3 = new FayeClient("/"+LOGIN, mHandler, TOKEN,LOGIN);
            fayeClient3.start();
        }

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();


        String authorization = "Token token=" + TOKEN;
        Retrofit retrofit = new Retrofit(getApplicationContext(), this);
        retrofit.logged(LOGIN, authorization,mHandler);

        fayeClientList = new ArrayList<>();

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
        tabOne.setCustomView(R.layout.single_tab_layout_all);
        tabOne.setTabListener(this);
        ActionBar.Tab tabTwo = actionBar.newTab();
        tabTwo.setCustomView(R.layout.single_tab_layout_private);
        tabTwo.setTabListener(this);
        ActionBar.Tab tabThree = actionBar.newTab();
        tabThree.setCustomView(R.layout.single_tab_layout_users_list);
        tabThree.setTabListener(this);

        actionBar.addTab(tabOne);
        actionBar.addTab(tabTwo);
        actionBar.addTab(tabThree);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case BACKGROUND_OPERATION: {
                Bundle bundle = msg.getData();
                newMessage(bundle.getString("login"), bundle.getString("message"));
                break;
            }
            case USER_OPERATION: {
                Bundle bundle = msg.getData();
                user_sign_in_out(bundle.getString("user"), bundle.getBoolean("activity"));
                break;
            }
            case LOGGIN:{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragmentC.updateUsersList();
                    }
                });


            }
        }

        return true;
    }

    private void user_sign_in_out(String user, boolean activity) {
        Log.d(TAG+"/user_sign_in_out", user);
        Log.d(TAG+"/user_sign_in_out", String.valueOf(activity));
        if (activity)
            myApplication.getUsersList().add(new User(user,true));
        else
            myApplication.getUsersList().remove(new User(user,false));
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                fragmentC.updateUsersList();
            }
        });
        Log.d(TAG + "LIST SIZE IS", "" + myApplication.getUsersList().size());


    }

    private void newMessage(final String login, final String message) {
        Log.d("login", login);
        Log.d("message", message);
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                if (login.equals(LOGIN)) {
                    fragmentA.addMessage("Ja", message, true);
                } else {
                    fragmentA.addMessage(login, message, false);
                }
            }
        });

    }

    @Override
    public void send(String message) throws JSONException {
        fayeClient.send(LOGIN, message);
        fragmentA.addMessage("Ja", message, true);
    }


    @Override
    public void notifyPrivateList() {
        fragmentB.updatePrivateList();
    }


    @Override
    public void sendAgain(int position, String message) throws JSONException {
        fayeClient.send(LOGIN, message);
        fragmentA.removeMessageFromList(position, message, LOGIN);

    }

    @Override
    public void sendPrivetMessage(String user, String message) {
        Log.d(TAG+"/sendMesagePrivet", user);
        Log.d(TAG+"/sendMesagePrivet", message);
        boolean send = false;
        for (FayeClient f : fayeClientList) {
            if (f.getNameChannel().equals("/" + user)) {
                send = true;
                try {
                    f.send(LOGIN, message);
                    Log.d(TAG, "wyslano");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (send == false) {
            FayeClient f = new FayeClient("/" + user, mHandler, TOKEN,LOGIN);
            fayeClientList.add(f);
            f.run(message);
        }
    }


    private class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int i) {

            if (i == 0) {
                return fragmentA;
            } else if (i == 1)
                return fragmentB;
            else
                return fragmentC;
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
            ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
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
            myApplication.getUsersList().clear();
            fayeClient.unsubscribe();
            fayeClient2.unsubscribe();
            clearListFaye();
            if (Network.isNetworkAvailable(this)) {
                Retrofit retrofit = new Retrofit(getApplicationContext(), this);
                String authorization = "Token token=" + TOKEN;
                retrofit.sign_out(authorization);
            }
            else {
                //Toast.makeText(getApplicationContext(),"There is no internect connection", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            //String authorization = "Basic ";
                    //Log.e(TAG, String.valueOf(response.getStatus()));
                    //Log.e(TAG, error.toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        myApplication.getUsersList().clear();
        fayeClient.unsubscribe();
        fayeClient2.unsubscribe();
        clearListFaye();

        if (Network.isNetworkAvailable(this)) {
            Retrofit retrofit = new Retrofit(getApplicationContext(), this);
            String authorization = "Token token=" + TOKEN;
            retrofit.sign_out(authorization);


        }
        else {
            //Toast.makeText(getApplicationContext(), "There is no internect connection", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
/*        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();*/
    }

    private void clearListFaye() {
        for (FayeClient f : fayeClientList) {
            f.unsubscribe();
        }
        fayeClientList.clear();
    }


}
