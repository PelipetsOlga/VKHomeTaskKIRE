package com.example.olga.vkhometaskkire.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

public class BigPhotoActivity extends AppCompatActivity {
    private int mode;
    public static final int SHOW_ONE_PHOTO = 1;
    public static final int SHOW_ALL_PHOTOS = 2;
    private ActionBar actionBar;
    private int currentPhotoPosition;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);
        image = (ImageView) findViewById(R.id.big_image);

        Intent intent = getIntent();
        mode = intent.getIntExtra(UtilsVK.TAG_SHOW_PHOTO_MODE, SHOW_ONE_PHOTO);

        if (mode == SHOW_ONE_PHOTO) {
            actionBar = getSupportActionBar();
            actionBar.hide();

            String path = intent.getStringExtra(UtilsVK.TAG_PATH);
            image.setImageBitmap(UtilsVK.getBitmapFromAssets(this, path));
        } else {
            int userId = intent.getIntExtra(UtilsVK.TAG_ID, 1);
            ArrayList<User> list = UtilsVK.getList();
            User user = null;
            for (User us : list) {
                if (us.getId() == userId) {
                    user = us;
                    break;
                }
            }
            if (user != null) {
                actionBar = getSupportActionBar();
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setTitle(user.getName());

                final String[] photos = user.getPhotos();
                if (photos != null && photos.length != 0) {

                    currentPhotoPosition = 0;
                    image.setImageBitmap(UtilsVK.getBitmapFromAssets(BigPhotoActivity.this, photos[currentPhotoPosition]));
                    image.setOnTouchListener(new OnSwipeTouchListener(BigPhotoActivity.this) {
                        @Override
                        public void onSwipeLeft() {
                            currentPhotoPosition--;
                            if (currentPhotoPosition<0){
                                currentPhotoPosition=photos.length-1;
                            }
                            image.setImageBitmap(UtilsVK.getBitmapFromAssets(BigPhotoActivity.this, photos[currentPhotoPosition]));
                        }

                        @Override
                        public void onSwipeRight() {
                            currentPhotoPosition++;
                            if (currentPhotoPosition>=photos.length){
                                currentPhotoPosition=0;
                            }
                            image.setImageBitmap(UtilsVK.getBitmapFromAssets(BigPhotoActivity.this, photos[currentPhotoPosition]));
                        }
                    });
                } else {
                    this.finish();
                }
            }
        }


    }

    private class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

}
