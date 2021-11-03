package com.wz.ximating.presenters;

import android.util.Log;

import androidx.annotation.Nullable;

import com.wz.ximating.constant.Constants;
import com.wz.ximating.interfaces.IAlbumDetailCallBack;
import com.wz.ximating.interfaces.IAlbumPresenter;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.XmAlbumTracksStatue;
import com.ximalaya.ting.android.opensdk.model.album.XmTrackStatue;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumDetailPresenter implements IAlbumPresenter {
    private Album mAlbum;
    private List<IAlbumDetailCallBack>mCallBacks = new ArrayList<>();
    private AlbumDetailPresenter(){}
    private static AlbumDetailPresenter sInstance = null;
    public static AlbumDetailPresenter getInstance(){
        if (sInstance == null){
            synchronized (AlbumDetailPresenter.class){
                sInstance = new AlbumDetailPresenter();
            }
        }
        return sInstance;
    }

    @Override
    public void loadAlbumDetailData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.ALBUM_ID, mAlbum.getId() + "");
        map.put(DTransferConstants.SORT, "asc");
        map.put(DTransferConstants.PAGE, "1");

        CommonRequest.getInstanse().setDefaultPagesize(20);
        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(@Nullable TrackList trackList) {
                if (trackList != null){
                    Log.d("AlbumDetailPresenter","" + trackList.getTracks().size());
                    for (IAlbumDetailCallBack mCallBack : mCallBacks) {
                        mCallBack.loadAlbumDetailDataCallBack(trackList.getTracks());
                    }
                }

            }
            @Override
            public void onError(int i, String s) {
                Log.d("AlbumDetailPresenter","" + s);
            }
        });
    }

    @Override
    public void registerCallBack(IAlbumDetailCallBack callBack) {
        Log.d("registerCallBack","registerCallBack");
        if (!mCallBacks.contains(callBack)){
            mCallBacks.add(callBack);
            callBack.onAlbumLoaded(mAlbum);
        }
    }

    @Override
    public void cancelRegisterCallBack(IAlbumDetailCallBack callBack) {
        if (mCallBacks.contains(callBack)){
            mCallBacks.remove(callBack);
        }
    }

    public void setAlbum(Album album){
        Log.d("setAlbum","setAlbum");
        mAlbum = album;
    }
}
