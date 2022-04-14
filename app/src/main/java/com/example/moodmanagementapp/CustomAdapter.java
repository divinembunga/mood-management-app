package com.example.moodmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {
    Context context;
    int logos[];
    String text[];
    LayoutInflater inflater;

    public CustomAdapter(Context context, int[] logos, String[] text){
        this.context=context;
        this.logos=logos;
        this.text=text;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {

        return logos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_gridview,null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        TextView iconText = (TextView) view.findViewById(R.id.icon_text);

        icon.setImageResource(logos[i]);
        iconText.setText(text[i]);

        return view;
    }
    @Override
    public boolean isEnabled(int position){
        return true;
    }
}
