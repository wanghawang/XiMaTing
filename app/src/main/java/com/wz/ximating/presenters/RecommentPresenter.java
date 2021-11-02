package com.wz.ximating.presenters;

import android.util.Log;

import androidx.annotation.Nullable;

import com.wz.ximating.interfaces.IRecomentPresenter;
import com.wz.ximating.interfaces.RecommentCallBack;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommentPresenter implements IRecomentPresenter {
    private final List<RecommentCallBack> mCallBacks = new ArrayList<>();
    //私有化构造方法
    private RecommentPresenter() { }
    //
    private static RecommentPresenter sInstance = null;
    //懒汉式单例
    public static RecommentPresenter getInstance(){
        if (sInstance == null){
            synchronized (RecommentPresenter.class) {
                sInstance = new RecommentPresenter();
            }
        }
        return sInstance;
    }

    @Override
    public void loadData() {
        getRecommentData();
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void regsterCallBack(RecommentCallBack callBack) {
        if (!mCallBacks.contains(callBack)) {
            mCallBacks.add(callBack);
        }
    }

    @Override
    public void cancelRegsterCallBack(RecommentCallBack callBack) {
        if (mCallBacks.contains(callBack)){
            mCallBacks.remove(callBack);
        }
    }
    ///加载推荐数据
    public void getRecommentData(){
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.LIKE_COUNT, "20");
        map.put(DTransferConstants.CATEGORY_ID,"0");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                Log.d("RecommentPresenter",gussLikeAlbumList.getAlbumList().size() + "");
                upDateRecommendUI(gussLikeAlbumList.getAlbumList());
            }
            @Override
            public void onError(int i, String s) {
                Log.d("RecommentPresenter",s);
            }
        });
    }

    private void upDateRecommendUI(List<Album>albumList){
        for (RecommentCallBack mCallBack : mCallBacks) {
            mCallBack.loadDataCallBack(albumList);
        }
    }
}
