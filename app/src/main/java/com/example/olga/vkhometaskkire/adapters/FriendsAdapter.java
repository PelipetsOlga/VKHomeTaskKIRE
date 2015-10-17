package com.example.olga.vkhometaskkire.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 17.10.2015.
 */
public class FriendsAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;
    private ArrayList<User> list;
    private Context ctx;

    public FriendsAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = (ArrayList<User>) objects;
        ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_list_friends, null);
        } else {
            view = convertView;
        }

        ImageView icon = (ImageView) view.findViewById(R.id.iv_photo_friend);
        User user = list.get(position);
        String[] photos = user.getPhotos();
        if (photos != null && photos.length != 0) {
            Bitmap bitmap = UtilsVK.getBitmapFromAssets(ctx, photos[0]);
            icon.setImageBitmap(bitmap);
        }else{
            icon.setImageResource(R.mipmap.ic_launcher);
        }

        TextView tvName= (TextView) view.findViewById(R.id.tv_name_friend);
        tvName.setText(user.getName());
        return view;
    }
}
