package com.example.olga.vkhometaskkire.datas;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.VideoView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.models.AudioTreck;
import com.example.olga.vkhometaskkire.models.Group;
import com.example.olga.vkhometaskkire.models.User;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Olga on 13.10.2015.
 */
public class UtilsVK {
    public static final String TAG_ID = "tag_id";
    public static final String TAG_ID_ARRAY = "tag_id_array";
    public static final String TAG_PATH = "tag_path";
    public static final String TAG_TITLE = "tag_title";
    public static final String TAG_SHOW_PHOTO_MODE = "show_photo_mode";




    public static Bitmap getBitmapFromAssets(Context ctx, String strName) {
        AssetManager assetManager = ctx.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
