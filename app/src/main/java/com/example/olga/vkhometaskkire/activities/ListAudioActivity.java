package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.AudioAdpter;
import com.example.olga.vkhometaskkire.adapters.VideoAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.AudioTreck;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;

public class ListAudioActivity extends AppCompatActivity {

    private ArrayList<AudioTreck> myAudio;
    private ListView lv;
    private AudioAdpter adapter;

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
        myAudio = new ArrayList<AudioTreck>();

        for (AudioTreck link : list) {
            if (link.isMember(audioIds)) {
                myAudio.add(link);
            }
        }

        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.lv_audio);
        adapter = new AudioAdpter(this, 0, myAudio);
        lv.setAdapter(adapter);

      /*  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListVideosActivity.this, BigVideoActivity.class);
                intent.putExtra(UtilsVK.TAG_ID, myVideo.get(position).getId());
                startActivity(intent);

            }
        });*/
    }

}
