package com.example.olga.vkhometaskkire.datas;

import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

/**
 * Created by Olga on 13.10.2015.
 */
public class UtilsVK {
    public static final String TAG_ID="tag_id";

    public static ArrayList<User> getList() {
        return list;
    }

    public static void setList() {
       list=new ArrayList<User>();
        list.add(new User(User.SEX_WOMAN,"Olga", "Pelipets","Olyalya", "Kharkov", "Ukraine", "photo_my.png", null, "undefinied", null, true,true, true, true, true, 0, 0, null));

    }

    private static ArrayList<User> list;


}
