package com.ekoapp.simplechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ekoapp.ekosdk.EkoChannel;
import com.ekoapp.ekosdk.EkoChannelRepository;
import com.ekoapp.ekosdk.EkoClient;
import com.ekoapp.simplechat.chatkit.ChatKitChannelListActivity;
import com.google.common.base.MoreObjects;

import butterknife.BindView;
import io.reactivex.Completable;

public class ChannelListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.channel_list_recyclerview)
    RecyclerView channelListRecyclerView;

    private final EkoChannelRepository channelRepository = EkoClient.newChannelRepository();


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_channel_list);
        setSupportActionBar(toolbar);

        String userId = MoreObjects.firstNonNull(EkoClient.getUserId(), "android_user_id_" + System.currentTimeMillis());
        String displayName = MoreObjects.firstNonNull(EkoClient.getDisplayName(), "Android");

        register(userId, displayName);

        channelRepository.getOrCreateById("show_me_the_money_th_finale", EkoChannel.Type.STANDARD);
        channelRepository.getOrCreateById("the_voice_thailand_s6_w15", EkoChannel.Type.STANDARD);

        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show());

        ChannelListAdapter adapter = new ChannelListAdapter();
        channelListRecyclerView.setAdapter(adapter);

        channelRepository.getChannelCollection()
                .observe(this, adapter::submitList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_channel_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_user_id) {
            showDialog(R.string.change_user_id, EkoClient.getUserId(), (dialog, input) -> {
                String userId = String.valueOf(input);
                register(userId, userId);
            });
            return true;
        } else if (id == R.id.action_change_display_name) {
            showDialog(R.string.change_display_name, EkoClient.getDisplayName(), (dialog, input) -> {
                String displayName = String.valueOf(input);
                EkoClient.setDisplayName(displayName)
                        .subscribe();
            });
        } else if (id == R.id.action_join_channel) {
            showDialog(R.string.join_channel, "", (dialog, input) -> {
                String channelId = String.valueOf(input);
                channelRepository.getOrCreateById(channelId, EkoChannel.Type.STANDARD);
            });
        } else if (id == R.id.action_chatkit) {
            Intent chatKit = new Intent(this, ChatKitChannelListActivity.class);
            startActivity(chatKit);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(@StringRes int title, CharSequence prefill, MaterialDialog.InputCallback callback) {
        new MaterialDialog.Builder(this)
                .title(title)
                .inputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .input(null, prefill, false, callback)
                .show();
    }

    private void register(String userId, String displayName) {
        EkoClient.registerDevice(userId, displayName)
                .andThen(Completable.fromAction(() -> {
                    String publicChannel = "public_eko";
                    channelRepository.getOrCreateById(publicChannel, EkoChannel.Type.STANDARD);
                    channelRepository.setDisplayName(publicChannel, "Public Eko standard channel");
                }))
                .subscribe();
    }
}
