package com.example.santi.mineswiper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getData();
        graella.setAdapter(new GrillAdapter(this,table,sizeGrill));

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

    private int calculateMinesToGrill(int size, int percent){
        return (int)((size*size)*(percent/100.0));
    }

    public void createTable(){
        table = new ArrayList<>();
        Element e = new Element();
        e.isCovered = true;
        e.isQuestioned = false;
        e.isUncovered = false;
        e.isMined = false;
        e.numMinesAround = 0;
        for (int i = 0; i < sizeGrill; i++){
            for (int j = 0; j < sizeGrill; j++){
                table[i][j] = e;
            }
        }
        /*table[0][2].isMined = true;
        table[1][1].isMined = true;
        table[1][3].isMined = true;
        table[0][4].isMined = true;
        table[2][3].isMined = true;
        table[4][3].isMined = true;*/
        checkMinesAround();
       /* Random randomGenerator = new Random();
        int randomX, mines = 0;
        while (mines < minesToBomb){
            for(int i = 0; i < sizeGrill; i++){
                for (int j = 0; j < sizeGrill; j++){
                    randomX = randomGenerator.nextInt(3);
                    if (!table[i][j].isMined && randomX == 0){
                        table[i][j].isMined = true;
                        mines = mines + 1;
                    }
                }
            }
        }*/
    }
    public void checkMinesAround() {
        for (int i = 0; i < sizeGrill; i++) {
            for (int j = 0; j < sizeGrill; j++) {
                if (i == 0 && j == 0) {
                    if (!table[i][j].isMined) {
                        if (table[i + 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (i == sizeGrill - 1 && j == 0) {
                    if (!table[i][j].isMined) {
                        if (table[i - 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (i == 0 && j == sizeGrill - 1) {
                    if (!table[i][j].isMined) {
                        if (table[i][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j - 1].isMined()) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (i == sizeGrill - 1 && j == sizeGrill - 1) {
                    if (!table[i][j].isMined) {
                        if (table[i][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j - 1].isMined()) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (i == 0) {
                    if (!table[i][j].isMined) {
                        if (table[i][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (j == 0) {
                    if (!table[i][j].isMined) {
                        if (table[i - 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    }
                } else if (i == sizeGrill - 1) {
                    if (!table[i][j].isMined) {
                        if (table[i][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j - 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i - 1][j + 1].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                        if (table[i + 1][j].isMined) {
                            table[i][j].setNumMinesAround(+1);
                        }
                    } else {
                        if (!table[i][j].isMined) {
                            if (table[i - 1][j - 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i - 1][j].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i - 1][j + 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i][j + 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i + 1][j + 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i + 1][j].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i + 1][j - 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                            if (table[i][j - 1].isMined) {
                                table[i][j].setNumMinesAround(+1);
                            }
                        }
                    }
                }
            }
        }
    }
}
