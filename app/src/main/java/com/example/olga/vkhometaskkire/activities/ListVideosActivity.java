package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.VideoView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.FriendsAdapter;
import com.example.olga.vkhometaskkire.adapters.VideoAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;

public class ListVideosActivity extends AppCompatActivity {

    private ArrayList<VideoRecord> currentList;
    private ArrayList<VideoRecord> myVideo;
    private VideoAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_videos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.videos_actionbar_title));

        Intent intent = getIntent();
        final int[] videosId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        ArrayList<VideoRecord> videos = UtilsVK.getListVideo();
        myVideo = new ArrayList<VideoRecord>();

        for (VideoRecord link : videos) {
            if (link.isMember(videosId)) {
                myVideo.add(link);
            }
        }

        initViews();
    }

    private void initViews() {
       lv = (ListView) findViewById(R.id.lv_videos);
        adapter = new VideoAdapter(this, 0, myVideo);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ListVideosActivity.this, BigVideoActivity.class);
                intent.putExtra(UtilsVK.TAG_ID, myVideo.get(position).getId());
                startActivity(intent);

            }
        });
    }


}