package com.wz.ximating;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wz.ximating.adapters.AlbunDetailAdapter;
import com.wz.ximating.base.BaseActivity;
import com.wz.ximating.interfaces.IAlbumDetailCallBack;
import com.wz.ximating.presenters.AlbumDetailPresenter;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.XmTrackStatue;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

public class DetailActivity extends BaseActivity implements IAlbumDetailCallBack {

    private AlbumDetailPresenter albumDetailPresenter;
    private ImageView bgImageV;
    private ImageView iconImageV;
    private TextView titleTv;
    private TextView contentTv;
    private AlbunDetailAdapter detailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //设置状态栏透明
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();

        albumDetailPresenter = AlbumDetailPresenter.getInstance();
        albumDetailPresenter.registerCallBack(this);
        albumDetailPresenter.loadAlbumDetailData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        detailAdapter = new AlbunDetailAdapter();
        detailAdapter.setOnItemClickListener(new AlbunDetailAdapter.ItemClickListener() {
            @Override
            public void itemClick(Track track) {
                Log.d("DetailActivity",track.getTrackTitle());
            }
        });
        RecyclerView recyclerView = findViewById(R.id.album_detail_recycle_view);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px(view.getContext(),10);
                outRect.left = UIUtil.dip2px(view.getContext(),10);
                outRect.right = UIUtil.dip2px(view.getContext(),10);
            }
        });
        recyclerView.setAdapter(detailAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        bgImageV = findViewById(R.id.album_detail_header_bg_imageV);
        iconImageV = findViewById(R.id.album_detail_header_icon_imageV);
        titleTv = findViewById(R.id.album_detail_header_title_tv);
        contentTv = findViewById(R.id.album_detail_header_content_tv);
    }

    @Override
    public void loadAlbumDetailDataCallBack(List<Track> tracks) {
        Log.d("DetailActivity","loadAlbumDetailDataCallBack");
        detailAdapter.setmData(tracks);
    }

    @Override
    public void onAlbumLoaded(Album album) {
        Log.d("DetailActivity","onAlbumLoaded");
        if (bgImageV != null){
            Picasso.with(this).load(album.getCoverUrlLarge()).into(bgImageV);
        }
        if (iconImageV != null){
            Picasso.with(this).load(album.getCoverUrlLarge()).into(iconImageV);
        }
        if (titleTv != null){
            titleTv.setText(album.getAlbumTitle());
        }
        if (contentTv != null){
            contentTv.setText(album.getAnnouncer().getNickname());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        albumDetailPresenter.cancelRegisterCallBack(this);
    }
}
