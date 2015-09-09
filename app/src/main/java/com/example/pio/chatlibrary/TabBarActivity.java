package com.example.pio.chatlibrary;

import android.app.ActionBar;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.pio.chatlibrary.fragments.FragmentA;
import com.example.pio.chatlibrary.fragments.FragmentB;
import com.example.pio.chatlibrary.fragments.FragmentC;

/**
 * Created by pio on 09.09.15.
 */
public class TabBarActivity extends FragmentActivity implements ActionBar.TabListener {

    private ActionBar actionBar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabviews);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
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

    private class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;

            if (i == 0) {
                fragment = new FragmentA();
            } else if (i == 1)
                fragment = new FragmentB();
            else if (i == 2)
                fragment = new FragmentC();

            return fragment;
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
}
