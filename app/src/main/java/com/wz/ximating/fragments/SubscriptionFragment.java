package com.wz.ximating.fragments;

import android.view.LayoutInflater;
import android.view.View;

import com.wz.ximating.R;
import com.wz.ximating.base.BaseFragment;

public class SubscriptionFragment extends BaseFragment {

    @Override
    public View onSubViewCreate(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_subscription,null,false);
    }
}
