package com.salami.projetobolos;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class adapter extends BaseAdapter {
    private int []list;
    private Context ctx;
    public adapter(Context ctx, int[]list){
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = new ImageView(ctx);
        iv.setImageResource(list[position]);
        iv.setLayoutParams(new ViewGroup.LayoutParams(180,180));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(8,8,8,8);
        return iv;
    }
}
