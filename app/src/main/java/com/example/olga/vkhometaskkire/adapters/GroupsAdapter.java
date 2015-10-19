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
import com.example.olga.vkhometaskkire.models.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 18.10.2015.
 */
public class GroupsAdapter extends ArrayAdapter<Group> {
    private LayoutInflater inflater;
    private ArrayList<Group> list;
    private Context ctx;

    public  GroupsAdapter(Context context, int resource, List<Group> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = (ArrayList<Group>) objects;
        ctx = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_list_groups, null);
        } else {
            view = convertView;
        }

        ImageView icon = (ImageView) view.findViewById(R.id.iv_icon_group);
        Group group = list.get(position);
        String iconPath  = group.getIcon();
        if (!TextUtils.isEmpty(iconPath)) {
            Bitmap bitmap = UtilsVK.getBitmapFromAssets(ctx, iconPath);
            icon.setImageBitmap(bitmap);
        }else{
            icon.setImageResource(R.mipmap.ic_launcher);
        }

        TextView tvName= (TextView) view.findViewById(R.id.tv_title_group);
        tvName.setText(group.getTitle());

        TextView tvClosed= (TextView) view.findViewById(R.id.tv_is_closed);
        if (group.isClosed()){
            tvClosed.setText(ctx.getResources().getString(R.string.group_is_closed));
        }else{
            tvClosed.setText(ctx.getResources().getString(R.string.group_is_opened));
        }

        return view;
    }
}
