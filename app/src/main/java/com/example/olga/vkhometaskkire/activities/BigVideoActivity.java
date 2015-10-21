package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;

import static com.example.olga.vkhometaskkire.datas.UtilsVK.*;

public class BigVideoActivity extends ParentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_video);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    void onServiceReady() {
        Intent intent = getIntent();
        final int videoId = intent.getIntExtra(UtilsVK.TAG_ID, 1);
        ArrayList<VideoRecord> list = vkService.getListVideo();
        VideoRecord video = null;

        for (VideoRecord link : list) {
            if (link.getId() == videoId) {
                video = link;
            }
        }

        if (video == null) {
            finish();
        } else {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(video.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);

            VideoView videoView = (VideoView) findViewById(R.id.video);
            String videoSource = video.getLink();
            videoView.setVideoURI(Uri.parse(videoSource));
            videoView.setMediaController(new MediaController(BigVideoActivity.this));
            videoView.requestFocus(0);
            videoView.start();

        }
    }
}
