package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.AudioAdpter;
import com.example.olga.vkhometaskkire.adapters.VideoAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.AudioItem;
import com.example.olga.vkhometaskkire.models.AudioTreck;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;

public class ListAudioActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private ArrayList<AudioItem> myAudio;
    private ListView lv;
    private AudioAdpter adapter;
    private AudioManager am;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_audio);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.audio_actionbar_title));
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final int[] audioIds = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        ArrayList<AudioTreck> list = UtilsVK.getListAudio();
        myAudio = new ArrayList<AudioItem>();

        for (AudioTreck link : list) {
            if (link.isMember(audioIds)) {
                myAudio.add(new AudioItem(link, AudioItem.stateNone));
            }
        }
        if (myAudio == null || myAudio.size() == 0) finish();

        initViews();

        am = (AudioManager) getSystemService(AUDIO_SERVICE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.lv_audio);
        adapter = new AudioAdpter(this, 0, myAudio);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AudioItem currentItem = myAudio.get(position);
                cleanOtherAudioItems(currentItem);
                if (mediaPlayer!=null && mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                int currentState = currentItem.getState();

                switch (currentState) {

                    case AudioItem.stateNone:
                       currentItem.setState(AudioItem.statePlay);
                        break;

                    case AudioItem.statePlay:
                        currentItem.setState(AudioItem.statePause);
                        int resAudio=0;
                        if (currentItem.getTreck().getId()==1){
                            resAudio=R.raw.audio1;
                        }else{
                            resAudio=R.raw.audio2;
                        }
                        mediaPlayer = MediaPlayer.create(ListAudioActivity.this, resAudio);
                        mediaPlayer.start();
                        if (mediaPlayer == null)
                            return;
                        mediaPlayer.setOnCompletionListener(ListAudioActivity.this);
                        break;

                    case AudioItem.statePause:
                        currentItem.setState(AudioItem.stateNone);
                        if (mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                        break;
                }
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void cleanOtherAudioItems(AudioItem currentItem) {
        for (AudioItem item:myAudio){
            if (item!=currentItem){
                item.setState(AudioItem.stateNone);
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
