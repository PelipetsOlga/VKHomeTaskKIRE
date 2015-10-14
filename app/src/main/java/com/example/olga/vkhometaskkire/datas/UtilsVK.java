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
    public static final String TAG_ID="tag_id";
    public static final String TAG_PATH="tag_path";

    public static ArrayList<User> getList() {
        return list;
    }

    public static void setList() {
       list=new ArrayList<User>();
        list.add(new User(User.SEX_WOMAN,"Olga", "Pelipets","Olyalya", "Kharkov", "Ukraine", new String[]{"avatar1.jpg", "photo1.jpg", "photo2.jpg", "photo3.jpg", "photo4.jpg"}, null, "В активном поиске счастья жизни", null, true,true, true, true, true, 0, 0, new int[]{2,3}));

    }

    private static ArrayList<User> list;

  /*  public static Bitmap getBitmapFromAssets(Context ctx,String fileName) throws IOException{
        AssetManager assetManager = ctx.getResources().getAssets();
        InputStream istr = assetManager.open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }*/

    public static Bitmap getBitmapFromAssets(Context ctx,String strName)
    {
        AssetManager assetManager = ctx.getAssets();
        InputStream istr = null;
        Bitmap bitmap=null;
        try {
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


}
