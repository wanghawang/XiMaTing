package com.wz.ximating.interfaces;

public interface IAlbumPresenter {
    /**
     * 获取专辑详情数据
     */
    void loadAlbumDetailData();

    void registerCallBack(IAlbumDetailCallBack callBack);

    void cancelRegisterCallBack(IAlbumDetailCallBack callBack);
}
