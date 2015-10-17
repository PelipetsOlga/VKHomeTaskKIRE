package com.example.olga.vkhometaskkire.datas;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.olga.vkhometaskkire.models.User;

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

    public static ArrayList<User> getList() {
        return list;
    }

    public static void setList() {
        list = new ArrayList<User>();
        //main user id=1
        list.add(new User(1,User.SEX_WOMAN, "Olga", "Pelipets", "Olyalya", "Kharkov", "Ukraine",
                new String[]{"avatar1.jpg", "photo1.jpg", "photo2.jpg", "photo3.jpg", "photo4.jpg"},
                null, "В активном поиске жизненного счастья", null, true, true, true, true, true, 0, 0, new int[]{2, 3}));
        //user id=2
        list.add(new User(2,User.SEX_WOMAN, "Kate", "Djenkins", "Katyuha", "Kharkov", "Ukraine",
                new String[]{"photo5.jpg", "photo6.jpg", "photo7.jpg", "photo8.jpg"},
                null, "No Martini, no party.", null, true, true, true, true, true, 0, 0, new int[]{1, 3}));
        //user id=3
        list.add(new User(3,User.SEX_MAN, "Dennis", "Markins", "Den49", "Kiev", "Ukraine",
                new String[]{"photo9.jpg", "photo8.jpg", "photo7.jpg", "photo6.jpg"},
                null, "Life is speed", null, false, true, true, true, true, 0, 0, new int[]{1, 2,4}));
        //user id=4
        list.add(new User(4,User.SEX_MAN, "Mark", "Petson", "BoyOk", "New York", "USA",
                new String[]{"photo10.jpg"},
                null, "bla bla bla", null, false, true, true, true, true, 0, 0, new int[]{3}));

    }

    private static ArrayList<User> list;


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
