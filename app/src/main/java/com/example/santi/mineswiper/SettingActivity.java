package com.example.santi.mineswiper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private String userName;
    private int sizeGrill;
    private boolean checkControl;
    private int mines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
    public void goGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        if (!((EditText)findViewById(R.id.userName)).getText().toString().trim().isEmpty()){
            userName = ((EditText) findViewById(R.id.userName)).getText().toString();
            bundle.putString("USERNAME",userName);
            sizeGrill = Integer.parseInt(((EditText)findViewById(R.id.sizeGrill)).getText().toString());
            bundle.putInt("SIZEGRILL",sizeGrill);
            checkControl = ((CheckBox)findViewById(R.id.checkControl)).isChecked();
            bundle.putBoolean("CHECKCONTROL",checkControl);
            mines = getMines();
            bundle.putInt("MINES",mines);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else {
            showToast(R.string.msg1);
        }
    }
    private int getMines(){
        if(((RadioButton)findViewById(R.id.radioButton1)).isChecked()){
            mines = 15;
        }
        else if(((RadioButton)findViewById(R.id.radioButton2)).isChecked()){
            mines = 25;
        }
        else mines = 35;
        return mines;
    }
    private void showToast(int id){
        Toast.makeText(this, id,Toast.LENGTH_SHORT).show();
    }
}
