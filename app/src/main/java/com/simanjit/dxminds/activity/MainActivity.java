package com.simanjit.dxminds.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.simanjit.dxminds.model.Articles;
import com.simanjit.dxminds.model.NewsResponse;
import com.simanjit.dxminds.viewModel.NewsViewModel;
import com.simanjit.dxminds.adapter.PlansPagerAdapter;
import com.simanjit.dxminds.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    NewsViewModel model;
    TabLayout tab;
    ViewPager viewPager;
    ArrayList<List<Articles>> tabTitle = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.frameLayout);


        loadDetail();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });

    }

    public void loadDetail() {
        model = ViewModelProviders.of(this).get(NewsViewModel.class);

        model.getUserResponse().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(@Nullable NewsResponse newsResponse) {
                if (newsResponse != null) {

                    for (int k = 0; k < newsResponse.getArticles().size(); k++) {
                        tab.addTab(tab.newTab().setText("" + newsResponse.getArticles().get(k).getSource().getName()));
                        tabTitle.add(newsResponse.getArticles());
                    }
                    PlansPagerAdapter adapter = new PlansPagerAdapter
                            (getSupportFragmentManager(), tab.getTabCount(), tabTitle);
                    viewPager.setAdapter(adapter);
                    viewPager.setOffscreenPageLimit(1);
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

                    if (tab.getTabCount() == 2) {
                        tab.setTabMode(TabLayout.MODE_FIXED);
                    } else {
                        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "No news available", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}