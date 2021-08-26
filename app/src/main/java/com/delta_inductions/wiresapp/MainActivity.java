package com.delta_inductions.wiresapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GameView.transfer {
    private TextView timer;
    private GameView gameView ;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        gameView = findViewById(R.id.gameview);
        gameView.settransfer(this);
         countDownTimer = new CountDownTimer(10000, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {

                timer.setText("YOU LOST");
                Intent intent = new Intent(MainActivity.this,PostGame.class);
                intent.putExtra("game","YOU LOST");
                startActivity(intent);
                finish();
            }
        }.start();
    }

    @Override
    public void update(boolean won) {
        if(won)
        {
            countDownTimer.cancel();
            timer.setText("YOU WON");
            Intent intent = new Intent(MainActivity.this,PostGame.class);
            intent.putExtra("game","YOU WON");
            startActivity(intent);
            finish();
        }

    }
}