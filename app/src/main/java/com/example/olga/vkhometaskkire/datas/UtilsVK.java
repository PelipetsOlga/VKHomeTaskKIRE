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

    private static ArrayList<User> list;
    private static ArrayList<VideoRecord> listVideo;
    private static ArrayList<AudioTreck> listAudio;
    private static ArrayList<Group> listGroup;


    public static void setList() {
        list = new ArrayList<User>();
        //audio2 user id=1
        list.add(new User(1, User.SEX_WOMAN, "Ольга", "Пелипец", "Olyalya", "Харьков", "Украина",
                new String[]{"avatar1.jpg", "photo1.jpg", "photo2.jpg", "photo3.jpg", "photo4.jpg"},
                null, "В активном поиске жизненного счастья", null, true, true, true, true, true, 0, 0, new int[]{2, 3}, new int[]{1, 2}, new int[]{1, 2}, new int[]{1,3}));
        //user id=2
        list.add(new User(2, User.SEX_WOMAN, "Катя", "Юрьева", "Katyuha", "Харьков", "Украина",
                new String[]{"photo5.jpg", "photo6.jpg", "photo7.jpg", "photo8.jpg"},
                null, "No Martini, no party.", null, true, true, true, true, true, 0, 0, new int[]{1, 3}, new int[]{1}, new int[]{2}, new int[]{1,3}));
        //user id=3
        list.add(new User(3, User.SEX_MAN, "Денис", "Леонов", "Den49", "Киев", "Украина",
                new String[]{"photo9.jpg", "photo8.jpg", "photo7.jpg", "photo6.jpg"},
                null, "Life is speed", null, false, true, true, true, true, 0, 0, new int[]{1, 2, 4}, null, null, new int[]{1,2}));
        //user id=4
        list.add(new User(4, User.SEX_MAN, "Mark", "Petson", "BoyOk", "New York", "USA",
                new String[]{"photo10.jpg"},
                null, "bla bla bla", null, false, true, true, true, true, 0, 0, new int[]{3}, new int[]{2}, new int[]{1}, new int[]{2}));
        //user id=5
        list.add(new User(5, User.SEX_WOMAN, "Инна", "Кальчик", "СупЕргЁрл", "Донецк", "Украина",
                new String[]{"photo2.jpg", "photo8.jpg"},
                null, "Я пьяна, в квартире срач, I am happy very much!", null, true, true, true, true, true, 0, 0, new int[]{6}, null, null, new int[]{1,3}));
        //user id=6
        list.add(new User(6, User.SEX_MAN, "Николай Николаевич", "Н.", "Николай", "Жмеринка", "Украина",
                new String[]{"photo7.jpg"},
                null, "... ... ...", null, false, true, true, true, true, 0, 0, new int[]{5}, null, null, new int[]{1,2}));

        listVideo = new ArrayList<VideoRecord>();
        listVideo.add(new VideoRecord(1, "photo2.jpg", "Cat Dinner", "android.resource://com.example.olga.vkhometaskkire/" + R.raw.video1));
        listVideo.add(new VideoRecord(2, "photo6.jpg", "Cat a cat", "android.resource://com.example.olga.vkhometaskkire/" + R.raw.video2));

        listAudio = new ArrayList<AudioTreck>();
        listAudio.add(new AudioTreck(1, "Audio 1 title", "audio1.mp3"));
        listAudio.add(new AudioTreck(2, "Audio 2 title", "audio2.mp3"));

        listGroup=new ArrayList<Group>();
        listGroup.add(new Group(1,new int[]{1,2,3,5,6}, "Мы - украинцы", "group_people.png", false));
        listGroup.add(new Group(2,new int[]{3,4,6}, "Общество автолюбителей", "group_auto.png", false));
        listGroup.add(new Group(3,new int[]{1,2,5}, "Копилка рукоделия", "group_handmade.png", true));
    }

    public static ArrayList<User> getList() {
        return list;
    }

    public static ArrayList<VideoRecord> getListVideo() {
        return listVideo;
    }

    public static ArrayList<AudioTreck> getListAudio() {
        return listAudio;
    }

    public static ArrayList<Group> getListGroups() {
        return listGroup;
    }


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
