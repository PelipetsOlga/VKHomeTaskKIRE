package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;

public class BigPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);

        Intent intent=getIntent();
        String path=intent.getStringExtra(UtilsVK.TAG_PATH);

        ImageView image= (ImageView) findViewById(R.id.big_image);
        image.setImageBitmap(UtilsVK.getBitmapFromAssets(this, path));

    }

}
