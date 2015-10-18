package com.example.olga.vkhometaskkire.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.models.AudioItem;
import com.example.olga.vkhometaskkire.models.AudioTreck;

import java.util.ArrayList;

/**
 * Created by Olga on 18.10.2015.
 */
public class AudioAdpter extends ArrayAdapter<AudioItem> {
    private LayoutInflater inflater;
    private ArrayList<AudioItem> list;
    private Context ctx;

    public AudioAdpter(Context context, int resource, ArrayList<AudioItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = (ArrayList<AudioItem>) objects;
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

        AudioItem record = list.get(position);

        TextView tvName = (TextView) view.findViewById(R.id.tv_title_audio);
        tvName.setText(record.getTreck().getTitle());

        int state = record.getState();
        ImageView iconPlay = (ImageView) view.findViewById(R.id.iv_audio_play);
        ImageView iconPause = (ImageView) view.findViewById(R.id.iv_audio_pause);

        switch (state){
            case AudioItem.stateNone:
                iconPlay.setVisibility(View.INVISIBLE);
                iconPause.setVisibility(View.INVISIBLE);
                break;
            case AudioItem.statePlay:
                iconPlay.setVisibility(View.VISIBLE);
                iconPause.setVisibility(View.INVISIBLE);
                break;
            case AudioItem.statePause:
                iconPlay.setVisibility(View.INVISIBLE);
                iconPause.setVisibility(View.VISIBLE);
                break;

        }


        return view;
    }
}
