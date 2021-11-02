package com.wz.ximating.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wz.ximating.R;
import com.wz.ximating.adapters.RecommentContentAdapter;
import com.wz.ximating.base.BaseFragment;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommentFragment extends BaseFragment {

    private RecommentContentAdapter recommentContentAdapter;

    @Override
    public View onSubViewCreate(LayoutInflater inflater) {
        getRecommentData();
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

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recommentContentAdapter);


        return view;
    }

    public void getRecommentData(){

        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.LIKE_COUNT, "20");
        map.put(DTransferConstants.CATEGORY_ID,"0");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                Log.d("RecommentFragment",gussLikeAlbumList.getAlbumList().size() + "");

                upDateRecommendUI(gussLikeAlbumList.getAlbumList());
            }

            @Override
            public void onError(int i, String s) {
                Log.d("RecommentFragment",s);
            }
        });
    }

    private void upDateRecommendUI(List<Album>albumList) {
        recommentContentAdapter.setData(albumList);
    }
}
