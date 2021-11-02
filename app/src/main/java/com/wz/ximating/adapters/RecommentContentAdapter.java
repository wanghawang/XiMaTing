package com.wz.ximating.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wz.ximating.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecommentContentAdapter extends RecyclerView.Adapter<RecommentContentAdapter.InnerHolder> {
    private List<Album> mAlbums = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomment,parent,false);
        InnerHolder innerHolder = new InnerHolder(itemView);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.setData(mAlbums.get(position));
    }

    @Override
    public int getItemCount() {
        if (mAlbums != null){return mAlbums.size();}
        return 0;
    }

    public void setData(List<Album> albumList) {
        mAlbums.addAll(albumList);
        mAlbums.addAll(albumList);
        mAlbums.addAll(albumList);
        mAlbums.addAll(albumList);
        mAlbums.addAll(albumList);
        notifyDataSetChanged();
    }

    class InnerHolder extends RecyclerView.ViewHolder{
        private final ImageView iconV;
        private final TextView titleTv;
        private final TextView contentTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            iconV = itemView.findViewById(R.id.recommend_item_icon);
            titleTv = itemView.findViewById(R.id.recommend_item_title);
            contentTv = itemView.findViewById(R.id.recommend_item_content);
        }

        public void setData(Album album) {
            titleTv.setText(album.getAlbumTitle());
            contentTv.setText(album.getAlbumIntro());
            Picasso.with(itemView.getContext()).load(album.getCoverUrlLarge()).into(iconV);
        }
    }
}
