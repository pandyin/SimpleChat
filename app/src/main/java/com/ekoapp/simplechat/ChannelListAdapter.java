package com.ekoapp.simplechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekoapp.ekosdk.EkoChannel;
import com.ekoapp.simplechat.intent.ViewMessagesIntent;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;

import static com.ekoapp.simplechat.ChannelListAdapter.ChannelViewHolder;

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelViewHolder> {

    List<EkoChannel> staticChannels = Lists.newArrayList();


    ChannelListAdapter() {
        EkoChannel smtm = new EkoChannel();
        smtm.setChannelId("show_me_the_money_th_finale");
        EkoChannel tvt = new EkoChannel();
        tvt.setChannelId("the_voice_thailand_s6_w15");
        staticChannels.add(smtm);
        staticChannels.add(tvt);
    }

    @Override
    public int getItemCount() {
        return staticChannels.size();
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_channel, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        EkoChannel channel = staticChannels.get(position);
        holder.channelId = channel.getChannelId();
        holder.channelIdTextView.setText(String.valueOf(channel));
    }


    static class ChannelViewHolder extends BaseViewHolder {

        String channelId;

        @BindView(R.id.channel_id_textview)
        TextView channelIdTextView;


        ChannelViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                ViewMessagesIntent intent = new ViewMessagesIntent(context, channelId);
                context.startActivity(intent);
            });
        }
    }
}
