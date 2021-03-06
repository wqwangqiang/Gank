package com.app.pfh.gank.ui.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pfh.gank.GankApplication;
import com.app.pfh.gank.R;
import com.app.pfh.gank.fragment.CommonFragment;
import com.app.pfh.gank.fragment.FuliFragment;
import com.app.pfh.gank.utils.PrefsUtils;
import com.app.pfh.gank.utils.SnackBarUtils;
import com.app.pfh.gank.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class FenLei_activity extends AppCompatActivity {



    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NavigationView mNavigationView;

    //viewpager和tablayout用到的fragment和tab标题
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private List<String> mTabTitles = new ArrayList<String>();
    private CoordinatorLayout coordinatorLayout;
    private long exitTime= 0;
    private int snackBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int themeId = PrefsUtils.getThemeId(this);
        switch (themeId){
            case PrefsUtils.THEMEID_BLUE:
                setTheme(R.style.AppThemeBlue);
                break;
            case PrefsUtils.THEMEID_PURPLE:
                setTheme(R.style.AppThemePurple);
                break;
            case PrefsUtils.THEMEID_RED:
                setTheme(R.style.AppThemeRed);
                break;
        }
        setContentView(R.layout.activity_fenlei);
        initViews();
        //监听navigation里面的menuitem
        setupDrawerContent(mNavigationView);
        initViewPager();
        initTabLayout();


    }


    private void initViewPager() {
        CommonFragment androidFragment = CommonFragment.newInstance(UrlUtils.ANDROID);
        CommonFragment iOSFragment = CommonFragment.newInstance(UrlUtils.IOS);
        CommonFragment releaxFragment = CommonFragment.newInstance(UrlUtils.releax);
        CommonFragment expandFragment = CommonFragment.newInstance(UrlUtils.expand);
        CommonFragment frontFragment = CommonFragment.newInstance(UrlUtils.front);
        FuliFragment fuliFragment = new FuliFragment();
        mFragments.add(androidFragment);
        mFragments.add(iOSFragment);
        mFragments.add(fuliFragment);
        mFragments.add(releaxFragment);
        mFragments.add(expandFragment);
        mFragments.add(frontFragment);
        mTabTitles.add("Android");
        mTabTitles.add("iOS");
        mTabTitles.add("福利");
        mTabTitles.add("休息视频");
        mTabTitles.add("拓展资源");
        mTabTitles.add("前端");
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.get(position);
            }
        });
    }

    private void initTabLayout() {
//        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.id_fenlei_tab);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mNavigationView = (NavigationView) findViewById(R.id.id_nav);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_FenLei_content);

        mToolbar.setTitle("Gank.io");
        setSupportActionBar(mToolbar);
        //设置左上角那个图标有用
        ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.mipmap.menu_white);//自己设置那个图标，不然用默认的
        ab.setDisplayHomeAsUpEnabled(true);
        //下面设置点击图标展开navigation
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar,R.string.abc_action_bar_home_description,
                R.string.abc_action_bar_home_description_format);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


    }

    private void setupDrawerContent(NavigationView mNavigationView) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setCheckable(true);
                switch (item.getItemId()){
                    case R.id.nav_daily:
                        Snackbar.make(coordinatorLayout,"daily功能完善中",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_type:
                        Intent intent = new Intent(FenLei_activity.this, FenLei_activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                        finish();
                        break;
                    case R.id.nav_collect:
                        Intent intent1 = new Intent(FenLei_activity.this, Collection_activity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//                        finish();
                        break;
                    case R.id.nav_setting:
                        Intent intent2 = new Intent(FenLei_activity.this, Setting_activity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                        finish();

//                        Snackbar.make(coordinatorLayout, "setting功能完善中", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_info:
                        showAboutWindow();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void showAboutWindow() {
        View aboutView = LayoutInflater.from(FenLei_activity.this).inflate(R.layout.about_layout,null);
        TextView tv_dizhi = (TextView) aboutView.findViewById(R.id.github_dizhi);
        TextView tv_author = (TextView) aboutView.findViewById(R.id.github_author);
        tv_dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("https://github.com/afayp/Gank");
//                Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
                Intent intent = new Intent(FenLei_activity.this,Webview_activity.class);
                intent.putExtra("url","https://github.com/afayp/Gank");
                intent.putExtra("desc","关于");
                startActivity(intent);
            }
        });
        tv_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("https://github.com/afayp/Gank");
//                Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
                Intent intent = new Intent(FenLei_activity.this,Webview_activity.class);
                intent.putExtra("url","https://github.com/afayp/Gank");
                intent.putExtra("desc","关于");
                startActivity(intent);
            }
        });

        PopupWindow popupWindow = new PopupWindow(aboutView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.about_bg)));
        popupWindow.showAtLocation(mDrawerLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GankApplication.getRequestQueue().cancelAll("VolleyGet");
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        if((System.currentTimeMillis()-exitTime) > 2000){
            Snackbar.make(coordinatorLayout, "再按一次退出！", Snackbar.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
