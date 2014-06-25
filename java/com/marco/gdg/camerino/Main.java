package com.marco.gdg.camerino;


import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

public class Main extends FragmentActivity implements TabListener {
    ActionBar actionBar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d("VIVZ","onTabSelected at "+"position"+arg0+"from"+arg1+"with number of pixels="+arg2);

            }

            @Override
            public void onPageSelected(int arg0) {
                actionBar.setSelectedNavigationItem(arg0);
                Log.d("VIVZ","onTabSelected at "+"position"+arg0);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (arg0==ViewPager.SCROLL_STATE_IDLE){
                    Log.d("VIVZ","onPageScrollStateChanged Idle");
                }
                if (arg0==ViewPager.SCROLL_STATE_DRAGGING){
                    Log.d("VIVZ","onPageScrollStateChanged Dragging");
                }
                if (arg0==ViewPager.SCROLL_STATE_SETTLING){
                    Log.d("VIVZ","onPageScrollStateChanged Settling");

                }

            }
        });

        actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1=actionBar.newTab();
        tab1.setText("25 Giugno");
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar.newTab();
        tab2.setText("26 Giugno");
        tab2.setTabListener(this);

        ActionBar.Tab tab3=actionBar.newTab();
        tab3.setText("#IOCamerino");
        tab3.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
       // Log.d("VIVZ","onTabSelected at "+"position"+tab.getPosition()+"name"+tab.getText());
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
      //  Log.d("VIVZ","onTabSelected at "+"position"+tab.getPosition()+"name"+tab.getText());

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
       // Log.d("VIVZ","onTabSelected at "+"position"+tab.getPosition()+"name"+tab.getText());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                                    // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


class MyAdapter extends FragmentPagerAdapter{

    public MyAdapter(FragmentManager fm) {
       super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fragment=null;
        if(arg0==0){
            fragment=new PrimoGiornoIO();
        }
        if(arg0==1){
            fragment=new SecondoGiornoIo();
        }
        if(arg0==2){
            fragment=new TwitterEIO();
        }

        return fragment;
    }
}

