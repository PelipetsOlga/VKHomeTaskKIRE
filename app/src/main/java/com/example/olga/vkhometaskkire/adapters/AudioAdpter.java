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
import com.example.olga.vkhometaskkire.models.AudioTreck;
import com.example.olga.vkhometaskkire.models.VideoRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 18.10.2015.
 */
public class AudioAdpter extends ArrayAdapter<AudioTreck> {
    private LayoutInflater inflater;
    private ArrayList<AudioTreck> list;
    private Context ctx;

    public AudioAdpter(Context context, int resource, ArrayList<AudioTreck> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = (ArrayList<AudioTreck>) objects;
        ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_list_audio, null);
        } else {
            view = convertView;
        }

        //ImageView icon = (ImageView) view.findViewById(R.id.iv_audio_play);
        AudioTreck record = list.get(position);

        TextView tvName= (TextView) view.findViewById(R.id.tv_title_audio);
        tvName.setText(record.getTitle());
        return view;
    }
}
