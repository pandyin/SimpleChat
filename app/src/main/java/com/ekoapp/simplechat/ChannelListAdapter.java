package com.ekoapp.simplechat;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekoapp.ekosdk.adapter.EkoChannelAdapter;

import butterknife.BindView;

import static com.ekoapp.simplechat.ChannelListAdapter.ChannelViewHolder;

public class ChannelListAdapter extends EkoChannelAdapter<ChannelViewHolder> {

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_channel, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.channelIdTextView.setText(String.valueOf(position));
    }


    static class ChannelViewHolder extends BaseViewHolder {

        @BindView(R.id.channel_id_textview)
        TextView channelIdTextView;

        ChannelViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                //
            });
        }
    }
}
