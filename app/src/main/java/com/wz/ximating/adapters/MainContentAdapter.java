package com.wz.ximating.adapters;

import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wz.ximating.utils.FragmentCreater;

public class MainContentAdapter extends FragmentPagerAdapter {
    public MainContentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentCreater.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentCreater.FRAGMENT_COUNT;
    }
}
