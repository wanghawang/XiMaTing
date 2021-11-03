package com.wz.ximating.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wz.ximating.DetailActivity;
import com.wz.ximating.R;
import com.wz.ximating.adapters.RecommentContentAdapter;
import com.wz.ximating.base.BaseFragment;
import com.wz.ximating.interfaces.RecommentCallBack;
import com.wz.ximating.presenters.AlbumDetailPresenter;
import com.wz.ximating.presenters.RecommentPresenter;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommentFragment extends BaseFragment implements RecommentCallBack {

    private RecommentContentAdapter recommentContentAdapter;
    private RecommentPresenter recommentPresenter;

    @Override
    public View onSubViewCreate(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_recomment,null,false);
        RecyclerView recyclerView = view.findViewById(R.id.recomment_recycleview);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = UIUtil.dip2px(view.getContext(),5);
                outRect.bottom = UIUtil.dip2px(view.getContext(),5);
                outRect.left = UIUtil.dip2px(view.getContext(),5);
                outRect.right = UIUtil.dip2px(view.getContext(),5);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recommentContentAdapter = new RecommentContentAdapter();
        recommentContentAdapter.setOnItemClickListener(new RecommentContentAdapter.ItemClickListener() {
            @Override
            public void itemClick(int index,Album album) {
                AlbumDetailPresenter.getInstance().setAlbum(album);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recommentContentAdapter);
        //获取presenter层
        recommentPresenter = RecommentPresenter.getInstance();
        //加载数据
        recommentPresenter.loadData();
        //注册回调
        recommentPresenter.regsterCallBack(this);
        return view;
    }

    private void upDateRecommendUI(List<Album>albumList) {
        recommentContentAdapter.setData(albumList);
    }

    @Override
    public void loadDataCallBack(List<Album> albumList) {
        upDateRecommendUI(albumList);
    }

    @Override
    public void loadMoreDataCallBack(List<Album> albumList) {

    }

    @Override
    public void refreshDataCallBack(List<Album> albumList) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recommentPresenter.cancelRegsterCallBack(this);

    }
}
