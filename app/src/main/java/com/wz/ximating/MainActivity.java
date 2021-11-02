package com.wz.ximating;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.wz.ximating.adapters.IndicatorAdapter;
import com.wz.ximating.adapters.MainContentAdapter;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        MagicIndicator indicator = (MagicIndicator)findViewById(R.id.magic_indicator);

        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        IndicatorAdapter adapter = new IndicatorAdapter(this);
        adapter.setOnIndicatorTapClickListener(new IndicatorAdapter.IndicatorTapClickListener() {
            @Override
            public void onClick(int index) {
                if (viewPager != null){
                    viewPager.setCurrentItem(index);
                }
            }
        });
        navigator.setAdapter(adapter);
        indicator.setNavigator(navigator);

        viewPager = (ViewPager) findViewById(R.id.indicator_content);
        ViewPagerHelper.bind(indicator, viewPager);

        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new MainContentAdapter(manager));


    }
}