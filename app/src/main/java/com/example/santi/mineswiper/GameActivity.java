package com.example.santi.mineswiper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public GridView graella;
    private String userName;
    private int sizeGrill;
    private boolean checkControl;
    private int minesPercent;
    public int minesToBomb;
    public List<Element> table;
    private Bundle bundle;
    public GrillAdapter grillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getData();
        this.graella = (GridView)findViewById(R.id.graella);
        this.grillAdapter = new GrillAdapter(this, this.table, this.sizeGrill);
        this.graella.setAdapter(this.grillAdapter);
        //graella.setAdapter(new GrillAdapter(this,table,sizeGrill));
        /*GridView gridv = (GridView)findViewById(R.id.graella);
        ArrayAdapter<Element> gridAdapter = new ArrayAdapter<Element>(this, android.R.layout.simple_list_item_1,this.table);
        gridv.setAdapter(gridAdapter);
        gridv.setOnItemClickListener(new gridViewInfo());*/

    }
    private void getData(){
        bundle = getIntent().getExtras();
        userName = bundle.getString("USERNAME");
        sizeGrill = bundle.getInt("SIZEGRILL");
        checkControl = bundle.getBoolean("CHECKCONTROL");
        minesPercent = bundle.getInt("MINES");
        minesToBomb = calculateMinesToGrill(sizeGrill,minesPercent);
        createTable();
    }

    public int calculateMinesToGrill(int size, int percent){
        return (int)((size*size)*(percent/100.0));
    }

    public void createTable(){
        table = new ArrayList<Element>();
        int totalSizeGrill = sizeGrill *sizeGrill;
        int mines = 0;
        for(int i = 0; i < totalSizeGrill; i++) {
            Element e = new Element();

            e.setCovered(true);
            e.setQuestioned(false);
            e.setMined(false);
            e.setNumMinesAround(0);
            e.setPosition(i);

            table.add(e);
        }
        while (mines < minesToBomb){
            Random randomGenerator = new Random();
            int numRandom = randomGenerator.nextInt(totalSizeGrill);
            Element e = table.get(numRandom);
            if (!e.isMined()){
                e.setMined(true);
                table.set(numRandom,e);
                mines = mines + 1;
            }
        }
        checkMinesAround();
    }

    public void checkMinesAround() {
        int totalSizeGrill = sizeGrill*sizeGrill;
        List<Integer> num = new ArrayList<>();
        for (int i = 2; i < sizeGrill; i++){
            num.add((sizeGrill*i)-1);
        }
        for (int pos = 0; pos< totalSizeGrill; pos++){
             if (pos == 0) {
                 Element e = table.get(pos);
                 if (e.isMined()) {
                     putNumMines(pos + 1);
                     putNumMines(sizeGrill);
                     putNumMines(sizeGrill + 1);
                 }
             }
            else if (pos == sizeGrill - 1){
                Element e = table.get(pos);
                 if (e.isMined()){
                     putNumMines(pos - 1);
                     putNumMines((pos*2)+1);
                     putNumMines(pos*2);
                 }
            }
            else if (pos == totalSizeGrill - sizeGrill){
                Element e = table.get(pos);
                if (e.isMined()){
                    putNumMines(pos + 1);
                    putNumMines(pos - sizeGrill);
                    putNumMines(pos - sizeGrill + 1);
                }
            }
            else if (pos == totalSizeGrill - 1){
                Element e = table.get(pos);
                if (e.isMined()){
                    putNumMines(pos - 1);
                    putNumMines(((sizeGrill - 1) * sizeGrill) - 1);
                    putNumMines(((sizeGrill - 1) * sizeGrill) - 2);
                }
            }
            else if(pos > 0 && pos < sizeGrill - 1){
                 Element e = table.get(pos);
                 if (e.isMined()){
                     putNumMines(pos - 1);
                     putNumMines(pos + 1);
                     putNumMines(pos + sizeGrill);
                     putNumMines(pos + sizeGrill - 1);
                     putNumMines(pos + sizeGrill + 1);
                 }
             }
            else if (pos > totalSizeGrill - sizeGrill && pos < totalSizeGrill - 1){
                 Element e = table.get(pos);
                 if (e.isMined()){
                    putNumMines(pos - 1);
                    putNumMines(pos + 1);
                    putNumMines(pos - sizeGrill);
                    putNumMines(pos - sizeGrill + 1);
                    putNumMines(pos - sizeGrill - 1);
                 }
             }
            else if (pos%sizeGrill == 0 && pos != 0 && pos != totalSizeGrill-sizeGrill){
                 Element e = table.get(pos);
                 if (e.isMined()){
                     putNumMines(pos - sizeGrill);
                     putNumMines(pos - sizeGrill + 1);
                     putNumMines(pos + 1);
                     putNumMines(pos + sizeGrill);
                     putNumMines(pos + sizeGrill + 1);
                 }
             }
            else if(num.contains(pos)){
                 Element e = table.get(pos);
                 if ((e.isMined())){
                     putNumMines(pos - 1);
                     putNumMines(pos - sizeGrill - 1);
                     putNumMines(pos - sizeGrill);
                     putNumMines(pos + sizeGrill - 1);
                     putNumMines(pos + sizeGrill);
                 }
             }
            else{
                 Element e = table.get(pos);
                 if ((e.isMined())){
                     putNumMines(pos - 1);
                     putNumMines(pos - sizeGrill - 1);
                     putNumMines(pos - sizeGrill);
                     putNumMines(pos - sizeGrill + 1);
                     putNumMines(pos + 1);
                     putNumMines(pos + sizeGrill - 1);
                     putNumMines(pos + sizeGrill);
                     putNumMines(pos + sizeGrill + 1);
                 }
             }
            /*Cotinuar a partir d'aqui, falten els laterals i el centre*/
        }
    }
    private void putNumMines(int position){
        Element element = table.get(position);
        if (!element.isMined()){
            element.setNumMinesAround(element.getNumMinesAround()+1);
            table.set(position,element);
        }
    }

    /*private class gridViewInfo implements AdapterView.OnItemClickListener{

        Context context;

        @Override
        public void onItemClick(AdapterView<?> listv, View selectedView, int position, long id){

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
            //btn.setText(table.get(position));
            // btn.setOnClickListener(new MyOnClickListener(position));
            //btn.setId(position);
            return btn;
        }
    }*/

}


