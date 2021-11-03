package com.wz.ximating.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.XmTrackStatue;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IAlbumDetailCallBack {
    /**
     * 获取专辑详情的回调
     */
    void loadAlbumDetailDataCallBack(List<Track>trackStatues);

    void onAlbumLoaded(Album album);
}
