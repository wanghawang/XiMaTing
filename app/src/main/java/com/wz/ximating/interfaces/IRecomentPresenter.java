package com.wz.ximating.interfaces;

public interface IRecomentPresenter {

    /**
     * 加载数据
     */
    void loadData();
    /**
     * 加载更多数据
     */
    void loadMoreData();
    /**
     * 刷新数据
     */
    void refreshData();

    /**
     * 注册回调
     */
    void regsterCallBack(RecommentCallBack callBack);

    /**
     * 取消注册回调
     */
    void cancelRegsterCallBack(RecommentCallBack callBack);
}
