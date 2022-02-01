package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class UserTabLayout extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.m_logout:
                SharedPreferences sp = getApplicationContext().getSharedPreferences("LoginData", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;

            case R.id.m_btm_nav:
                Intent intent = new Intent(getApplicationContext(), BottomNav.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tab_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tab_layout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tab_layout.addTab(tab_layout.newTab().setText("Male Users"));
        tab_layout.addTab(tab_layout.newTab().setText("Female Users"));
        tab_layout.addTab(tab_layout.newTab().setText("Account"));

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout.selectTab(tab_layout.getTabAt(position));
            }
        });



    }
}