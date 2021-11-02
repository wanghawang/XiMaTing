package com.wz.ximating.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

public interface RecommentCallBack {
    /**
     * 返回推荐数据
     * @param albumList
     */
    void loadDataCallBack(List<Album>albumList);
    /**
     * 返回加载更多推荐数据
     * @param albumList
     */
    void loadMoreDataCallBack(List<Album>albumList);
    /**
     * 返回刷新推荐数据
     * @param albumList
     */
    void refreshDataCallBack(List<Album>albumList);
}
