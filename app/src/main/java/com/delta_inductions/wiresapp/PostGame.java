package com.delta_inductions.wiresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostGame extends AppCompatActivity {
 private TextView gametext;
 private Button Restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);
        Restart = findViewById(R.id.btn_res);
        gametext= findViewById(R.id.postgametext);
        gametext.setText(getIntent().getStringExtra("game"));
        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostGame.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}