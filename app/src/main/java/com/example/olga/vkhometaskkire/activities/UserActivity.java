package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.PhotosAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private int id;
    private TextView name, online, status, counterFriends, counterGroups, counterFotos;
    private LinearLayout btnFriends, btnGroups, btnFotos;
    private User user;
    private ActionBar actionBar;
    private ImageView avatar;
    private Gallery horizontalGridView;
    private String[] photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

        Intent intent = getIntent();
        id = intent.getIntExtra(UtilsVK.TAG_ID, 1);

        ArrayList<User> list = UtilsVK.getList();
        for (User us : list) {
            if (us.getId() == id) {
                user = us;
                break;
            }
        }
        if (user == null) user = list.get(0);

        restoreActionBar();
        initViews();

        setDatas(user);

    }

    private void setDatas(User user) {
        if (id == 1) {
            actionBar.setTitle(getResources().getString(R.string.profile));
        } else {
            actionBar.setTitle(user.getName());
        }

        photos = user.getPhotos();
        if (photos != null && photos.length != 0) {
            try {
                String avatarPath = photos[0];
                Bitmap bitmap = UtilsVK.getBitmapFromAssets(UserActivity.this, avatarPath);
                if (bitmap != null)
                    avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (photos != null && photos.length > 1) {
            PhotosAdapter photosAdapter = new PhotosAdapter(UserActivity.this, 0, photos);
            horizontalGridView.setAdapter(photosAdapter);
            horizontalGridView.setSelection(photos.length / 2);
            horizontalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(UserActivity.this, BigPhotoActivity.class);
                    intent.putExtra(UtilsVK.TAG_PATH, photos[position]);
                    startActivity(intent);

                }
            });
        }


        name.setText(user.getName());
        if (user.isOnline()) {
            online.setText(getResources().getString(R.string.online));
        } else {
            online.setText(getResources().getString(R.string.offline));
        }

        if (!TextUtils.isEmpty(user.getStatus())) {
            status.setText(user.getStatus());
        }
        if (user.getFriends() != null) {
            int countFriends = user.getFriends().length;
            counterFriends.setText(Integer.toString(countFriends));
        } else {
            counterFriends.setText("0");
        }
    }

    private void initViews() {
        avatar = (ImageView) findViewById(R.id.avatar_user);
        name = (TextView) findViewById(R.id.name);
        online = (TextView) findViewById(R.id.online);
        status = (TextView) findViewById(R.id.status);
        counterFriends = (TextView) findViewById(R.id.counter_friends);
        counterGroups = (TextView) findViewById(R.id.counter_groups);
        counterFotos = (TextView) findViewById(R.id.counter_fotos);
        btnFotos = (LinearLayout) findViewById(R.id.btn_fotos);
        btnFriends = (LinearLayout) findViewById(R.id.btn_friends);
        btnGroups = (LinearLayout) findViewById(R.id.btn_groups);
        btnFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] friendsId = user.getFriends();
                Intent friendsIntent = new Intent(UserActivity.this, ListFriendsActivity.class);
                friendsIntent.putExtra(UtilsVK.TAG_ID_ARRAY, friendsId);
                startActivity(friendsIntent);

            }
        });

        btnGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        horizontalGridView = (Gallery) findViewById(R.id.horizontal_gridView);
    }

    private void restoreActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.ic_menu));

    }


}
