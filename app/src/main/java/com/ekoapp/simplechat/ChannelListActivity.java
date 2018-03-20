package com.ekoapp.simplechat;

import android.os.Bundle;
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

        EkoClient.registerDevice("android_user_id", "Android 1")
                .andThen(Completable.fromAction(() -> channelRepository.getOrCreateById("show_me_the_money_th_finale", EkoChannel.Type.STANDARD)))
                .subscribe();

        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show());

        ChannelListAdapter adapter = new ChannelListAdapter();
        channelListRecyclerView.setAdapter(adapter);
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
            new MaterialDialog.Builder(this)
                    .title(R.string.change_user_id)
                    .inputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input(null, null, false, (dialog, input) -> {
                        String userId = String.valueOf(input);
                        changeUserId(userId);
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeUserId(String userId) {
        EkoClient.registerDevice(userId, userId)
                .andThen(Completable.fromAction(() -> {
                    //channelRepository.getChannelCollection();
                }))
                .subscribe();
    }
}
