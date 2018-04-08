package com.example.androidtechies.majorproject;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import com.example.androidtechies.majorproject.R;
import com.example.androidtechies.majorproject.FragmentOne;
import com.example.androidtechies.majorproject.FragmentTwo;
import com.example.androidtechies.majorproject.FragmentThree;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.intro_brief)
    TextView introDescription;
    @BindView(R.id.tech_brief)
    TextView techDescription;
    @BindView(R.id.collapsingtoolbar)
    CollapsingToolbarLayout cToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        InformationModel model = intent.getParcelableExtra("Information");
        String title = model.getTitleOfProject();
        String intro = model.getIntroProject();
        String tech = model.getTechnologyUsed();
        Log.d("Information", title+ " 2 "+intro+ " 3 "+tech);
        introDescription.setText(intro);
        techDescription.setText(tech);
        cToolBar.setTitle(title);

        toolbar = (Toolbar) findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "INTRO");
        adapter.addFragment(new FragmentTwo(), "TECH");
        adapter.addFragment(new FragmentThree(), "TEAM");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
