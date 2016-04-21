package com.example.santi.mineswiper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by santi on 21/04/16.
 */
public class GrillAdapter extends BaseAdapter {
    private int sizeGrill;
    Context context;
    Element[][]table;
    public GrillAdapter(Context context, Element[][]table, int sizeGrill) {
        this.context = context;
        this.table = table;
        this.sizeGrill = sizeGrill;
    }

    @Override
    public int getCount() {
        return sizeGrill*sizeGrill;
    }
/*Hem d'arreglar aquest mètode*/
    @Override
    public Element getItem(int position) {
        return table[position][position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null){
            btn = new Button(context);
            btn.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,50));
            btn.setPadding(8,8,8,8);
        }
        else{
            btn = (Button)convertView;
        }
        btn.setText(table.get(position));
        btn.setOnClickListener(new MyOnClickListener(position));
        btn.setId(position);
        return btn;
    }
}
