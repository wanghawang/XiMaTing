package com.wz.ximating.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wz.ximating.R;
import com.ximalaya.ting.android.opensdk.model.album.XmTrackStatue;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AlbunDetailAdapter extends RecyclerView.Adapter<AlbunDetailAdapter.InnerHolder> {
    private List<Track> mData = new ArrayList<>();
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat durationSimpleDateFormat = new SimpleDateFormat("mm分:dd秒");
    private ItemClickListener itemClickListener;

    public void setmData(List<Track> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbunDetailAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_detail,parent,false);
        InnerHolder innerHolder = new InnerHolder(itemView);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbunDetailAdapter.InnerHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.setData(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClick(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder{

        private final TextView titleTv;
        private final TextView timeTv;
        private final TextView idTv;
        private final TextView durationTv;
        private final TextView readCountTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.item_album_detail_title_tv);
            timeTv = itemView.findViewById(R.id.item_album_detail_time_tv);
            idTv = itemView.findViewById(R.id.item_album_detail_id_tv);
            durationTv = itemView.findViewById(R.id.item_album_detail_duration_tv);
            readCountTv = itemView.findViewById(R.id.item_album_detail_red_count_tv);
        }

        public void setData(Track track){
            idTv.setText(((int)itemView.getTag() + 1) + "");
            titleTv.setText(track.getTrackTitle());
            timeTv.setText(mSimpleDateFormat.format(track.getUpdatedAt()));
            //转化为毫秒
            int duration = track.getDuration() * 1000;
            durationTv.setText(durationSimpleDateFormat.format(duration));
            readCountTv.setText(track.getPlayCount() + "");
        }
    }

    public void setOnItemClickListener(ItemClickListener listener){
        itemClickListener = listener;
    }

    public interface ItemClickListener{
        void itemClick(Track track);
    }
}
