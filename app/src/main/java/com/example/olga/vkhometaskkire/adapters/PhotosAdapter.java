package com.example.olga.vkhometaskkire.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;

import java.util.List;

/**
 * Created by Olga on 14.10.2015.
 */
public class PhotosAdapter extends ArrayAdapter<String> {
    private String[] list;
    private Context ctx;


    public PhotosAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        list = objects;
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // if (position==0) return null;
        ImageView view= null;
        if (convertView==null){
          view=new  ImageView(ctx);
        }else{
            view=(ImageView)convertView;
        }
        view.setImageBitmap(UtilsVK.getBitmapFromAssets(ctx, list[position]));
        view.setPadding(10, 10, 10, 10);
        view.setLayoutParams(new Gallery.LayoutParams(150, 150));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setBackgroundResource(R.color.color_background_gray);
        return view;
        /*

        View itemView = convertView;
        if (itemView == null) {
            itemView = View.inflate(getContext(), R.layout.item_photo_horizontal_gridview, null);
        }
        ImageView icon = (ImageView) itemView.findViewById(R.id.iv_item);

        String pathIcon = list[position];

        icon.setImageBitmap(UtilsVK.getBitmapFromAssets(ctx, pathIcon));


        return itemView;*/
    }
}
