package com.ekoapp.simplechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.ekoapp.ekosdk.EkoClient;
import com.ekoapp.ekosdk.EkoMessageRepository;
import com.ekoapp.simplechat.intent.ViewMessagesIntent;

import butterknife.BindView;

public class MessageListActivity extends BaseActivity {

    @BindView(R.id.message_list_recyclerview)
    RecyclerView messageListRecyclerView;

    private final EkoMessageRepository messageRepository = EkoClient.newMessageRepository();
    private final MessageListAdapter adapter = new MessageListAdapter();


    @Override
    protected void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_message_list);

        final String channelId = ViewMessagesIntent.getChannelId(getIntent());
        setTitle(channelId);

        if (channelId != null) {
            messageListRecyclerView.setAdapter(adapter);
            messageRepository.getMessageCollection(channelId)
                    .observe(this, adapter::submitList);
        }
    }
}
