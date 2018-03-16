package com.ekoapp.simplechat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;

import static com.ekoapp.simplechat.MessageListAdapter.MessageViewHolder;

public class MessageListAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    @Override
    public int getItemCount() {
        return 10000;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        // FIXME implement this
        holder.textView.setText(String.valueOf(position));
    }


    static class MessageViewHolder extends BaseViewHolder {

        @BindView(R.id.message_textview)
        TextView textView;


        MessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
