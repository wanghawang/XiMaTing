package com.wz.ximating.utils;

import com.wz.ximating.base.BaseFragment;
import com.wz.ximating.fragments.HistoryFragment;
import com.wz.ximating.fragments.RecommentFragment;
import com.wz.ximating.fragments.SubscriptionFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentCreater {
    public static final int INDEX_RECOMMENT = 0;
    public static final int INDEX_SUBSCRIPTION = 1;
    public static final int INDEX_HISTORY = 2;

    public static final int FRAGMENT_COUNT = 3;
    private static Map<Integer, BaseFragment>sCaches = new HashMap<>();

    public static BaseFragment getFragment(int index){
        BaseFragment fragment = sCaches.get(index);
        if (fragment != null) return fragment;

        switch (index){
            case INDEX_RECOMMENT:
                fragment = new RecommentFragment();
                break;
            case INDEX_SUBSCRIPTION:
                fragment = new SubscriptionFragment();
                break;
            case INDEX_HISTORY:
                fragment = new HistoryFragment();
                break;
        }
        sCaches.put(index,fragment);
        return fragment;
    }
}
