package com.ekoapp.simplechat;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ekoapp.ekosdk.EkoMessage;
import com.ekoapp.ekosdk.EkoObjects;
import com.ekoapp.ekosdk.adapter.EkoMessageAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import butterknife.BindView;

import static com.ekoapp.simplechat.MessageListAdapter.MessageViewHolder;

public class MessageListAdapter extends EkoMessageAdapter<MessageViewHolder> {

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        EkoMessage message = getItem(position);

        if (EkoObjects.isProxy(message)) {
            holder.textView.setText(position + " loading...");
        } else {
            String userId = message.getUserId();
            String time = new DateTime(message.getCreatedAt()).toString(DateTimeFormat.shortDateTime());
            String text = message.getData().get("text").getAsString();
            holder.textView.setText(String.format("%s (%s):\n%s", userId, time, text));
        }

    }


    static class MessageViewHolder extends BaseViewHolder {

        @BindView(R.id.message_textview)
        TextView textView;


        MessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
