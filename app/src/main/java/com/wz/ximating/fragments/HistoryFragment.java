package com.wz.ximating.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wz.ximating.R;
import com.wz.ximating.base.BaseFragment;

import java.util.zip.Inflater;

public class HistoryFragment extends BaseFragment {
    @Override
    public View onSubViewCreate(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_history,null,false);
    }
}
