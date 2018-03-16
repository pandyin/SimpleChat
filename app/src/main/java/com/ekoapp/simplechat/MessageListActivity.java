package com.ekoapp.simplechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.ekoapp.simplechat.intent.ViewMessagesIntent;

public class MessageListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_message_list);

        final String channelId = ViewMessagesIntent.getChannelId(getIntent());
        setTitle(channelId);

        if (channelId != null) {
            // FIXME butter knife
            RecyclerView rv = findViewById(R.id.message_list_recyclerview);
            rv.setAdapter(new MessageListAdapter());
        }
    }
}
