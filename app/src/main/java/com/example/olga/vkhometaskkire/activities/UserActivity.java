package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
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
    private TextView name, online, status, counterFriends, counterGroups, counterFotos, counterAudio, counterVideo;
    private LinearLayout btnFriends, btnGroups, btnFotos, btnAudio, btnVideo;
    private Button btnWrite;
    private ImageButton btnCaptureOrGift;
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
            btnWrite.setText(getResources().getString(R.string.write_wall));
            btnCaptureOrGift.setImageResource(R.drawable.icon_camera);
        } else {
            actionBar.setTitle(user.getName());
            btnWrite.setText(getResources().getString(R.string.write_private));
            btnCaptureOrGift.setImageResource(R.drawable.icon_gift);
        }

        photos = user.getPhotos();

        if (photos == null || photos.length == 0) {
            btnFotos.setVisibility(View.GONE);
        } else {
            if (photos != null && photos.length != 0) {
                try {
                    counterFotos.setText(Integer.toString(photos.length));
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
                        intent.putExtra(UtilsVK.TAG_SHOW_PHOTO_MODE, BigPhotoActivity.SHOW_ONE_PHOTO);
                        intent.putExtra(UtilsVK.TAG_PATH, photos[position]);
                        startActivity(intent);

                    }
                });
            }
        }


        name.setText(user.getName());
        if (user.isOnline()) {
            online.setText(getResources().getString(R.string.online));
        } else {
            online.setText(getResources().getString(R.string.offline));
        }

        if (!TextUtils.isEmpty(user.getStatus())) {
            status.setText(user.getStatus());
        }else{
            status.setVisibility(View.GONE);
        }

        if (user.getFriends() == null || user.getFriends().length == 0) {
            counterFriends.setText("0");
        } else {
            int countFriends = user.getFriends().length;
            counterFriends.setText(Integer.toString(countFriends));
        }

        if (user.getVideos() == null || user.getVideos().length == 0) {
            counterVideo.setText("0");
        } else {
            int countVideo = user.getVideos().length;
            counterVideo.setText(Integer.toString(countVideo));
        }

        if (user.getAudio() == null || user.getAudio().length == 0) {
            counterAudio.setText("0");
        } else {
            int countAudio = user.getAudio().length;
            counterAudio.setText(Integer.toString(countAudio));
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
        counterAudio = (TextView) findViewById(R.id.counter_audio);
        counterVideo = (TextView) findViewById(R.id.counter_video);
        btnFotos = (LinearLayout) findViewById(R.id.btn_fotos);
        btnFriends = (LinearLayout) findViewById(R.id.btn_friends);
        btnGroups = (LinearLayout) findViewById(R.id.btn_groups);
        btnAudio = (LinearLayout) findViewById(R.id.btn_audio);
        btnVideo = (LinearLayout) findViewById(R.id.btn_video);
        btnWrite=(Button)findViewById(R.id.btn_write_wall_private);
        btnCaptureOrGift=(ImageButton)findViewById(R.id.btn_capture_gift);

        btnFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int userId = user.getId();
                Intent photosUser = new Intent(UserActivity.this, BigPhotoActivity.class);
                photosUser.putExtra(UtilsVK.TAG_SHOW_PHOTO_MODE, BigPhotoActivity.SHOW_ALL_PHOTOS);
                photosUser.putExtra(UtilsVK.TAG_ID, userId);
                startActivity(photosUser);

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

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] videosId = user.getVideos();
                Intent videoIntent = new Intent(UserActivity.this, ListVideosActivity.class);
                videoIntent.putExtra(UtilsVK.TAG_ID_ARRAY, videosId);
                startActivity(videoIntent);
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] audioId = user.getAudio();
                Intent audioIntent = new Intent(UserActivity.this, ListAudioActivity.class);
                audioIntent.putExtra(UtilsVK.TAG_ID_ARRAY, audioId);
                startActivity(audioIntent);
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
