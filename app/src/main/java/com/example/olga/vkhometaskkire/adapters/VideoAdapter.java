package com.example.olga.vkhometaskkire.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 17.10.2015.
 */
public class VideoAdapter extends ArrayAdapter<VideoRecord> {
    private LayoutInflater inflater;
    private ArrayList<VideoRecord> list;
    private Context ctx;

    public VideoAdapter(Context context, int resource, List<VideoRecord> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = (ArrayList<VideoRecord>) objects;
        ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_list_video, null);
        } else {
            view = convertView;
        }

        ImageView icon = (ImageView) view.findViewById(R.id.iv_preview_video);
        VideoRecord record = list.get(position);
        String previewPath = record.getPhoto_preview();
        if (!TextUtils.isEmpty(previewPath)) {
            Bitmap bitmap = UtilsVK.getBitmapFromAssets(ctx, previewPath);
            icon.setImageBitmap(bitmap);
        }else{
            icon.setImageResource(R.mipmap.ic_launcher);
        }

        TextView tvName= (TextView) view.findViewById(R.id.tv_title_video);
        tvName.setText(record.getTitle());
        return view;
    }
}
