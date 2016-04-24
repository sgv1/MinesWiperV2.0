package com.example.santi.mineswiper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

/**
 * Created by santi on 21/04/16.
 */
public class GrillAdapter extends BaseAdapter {
    private int sizeGrill;
    Context context;
    List<Element>table;

    public GrillAdapter(Context context, List<Element>table, int sizeGrill) {
        this.context = context;
        this.table = table;
        this.sizeGrill = sizeGrill;
    }

    @Override
    public int getCount() {
        return sizeGrill*sizeGrill;
    }

    @Override
    public Element getItem(int position) {
        return table.get(position);
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
            btn.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT,GridView.LayoutParams.WRAP_CONTENT));
            btn.setPadding(8,8,8,8);
        }
        else{
            btn = (Button)convertView;
            btn.setPadding(8,8,8,8);
        }
        //btn.setText(table.get(position));
        btn.setId(position);
        btn.setOnClickListener(new MyOnClickListener(position));
        return btn;
    }

    public class MyOnClickListener implements View.OnClickListener{

        private final int position;
        public MyOnClickListener(int position){
            this.position=position;
        }
        @Override
        public void onClick(View view){
            Button btn = (Button) view.findViewById(view.getId());
            Element e = table.get(position);
            if(e.isCovered()){
                e.setCovered(false);
                if(e.isMined()){
                    //btn.setBackgroundResource(R.drawable.bomb);
                    btn.setBackgroundColor(android.graphics.Color.RED);
                    //passar a resultats; pa que el santi se n'enteri
                }
                else{
                    btn.setText(e.getNumMinesAround());
                }
            }
        }

    }
}
