package com.example.santi.mineswiper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Button btnNewGame = (Button) findViewById(R.id.buttonNewGame);
        Button btnSendEmail = (Button)findViewById(R.id.buttonSendEmail);
        Button btnExit = (Button)findViewById(R.id.buttonExit);

        btnNewGame.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonExit:
                finish();
                break;
            case R.id.buttonSendEmail:
                Intent gmail = new Intent(Intent.ACTION_VIEW);
                gmail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                gmail.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email)});
                gmail.setData(Uri.parse(getString(R.string.email)));
                gmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
                gmail.setType("plain/text");
                gmail.putExtra(Intent.EXTRA_TEXT, getString(R.string.body));
                startActivity(gmail);
                break;
            case R.id.buttonNewGame:
                startActivity(new Intent(this,SettingActivity.class));
                finish();
                break;
        }
    }
}
