package com.example.santi.mineswiper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goHelp(View v){
        startActivity(new Intent(this,HelpActivity.class));
        finish();
    }
    public void goSetting(View v){
        startActivity(new Intent(this,SettingActivity.class));
        finish();
    }
    public void goExit(View v){
        finish();
    }
}
