package com.delta_inductions.wiresapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameView extends View {
    private static final String TAG = "GameView";
    public static final int WIDTH = 50;
    private float arr[] = {200,500,700,900,1400,1200};
    private Paint paintR = new Paint();
    private Paint paintG = new Paint();
    private Paint paintB = new Paint();
    private float ly1 = 100;
    private float ly2 = 200;
    private float ly3 = 300;
    private float ly4 = 400;
    private float ly5 = 500;
    private float ly6 = 600;
    private boolean start = false;
    private boolean touchedly1 = false;
    private boolean touchedly2 = false;
    private boolean touchedly3 = false;
    private boolean touchedly4 = false;
    private boolean touchedly5 = false;
    private boolean touchedly6 = false;
    private boolean between12 = true;
    private boolean between34 = true;
    private boolean between56 = true;
    private boolean stop = false;
    private boolean won = false;
    private transfer obj;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!stop) {
            if (!start) {
                Randomorder();
            }
            paintB.setColor(Color.BLUE);
            paintG.setColor(Color.GREEN);
            paintR.setColor(Color.RED);
            paintR.setStrokeWidth(WIDTH);
            paintG.setStrokeWidth(WIDTH);
            paintB.setStrokeWidth(WIDTH);
            canvas.drawLine(0, ly1, getWidth(), ly1, paintR);
            canvas.drawLine(0, ly2, getWidth(), ly2, paintR);
            canvas.drawLine(0, ly3, getWidth(), ly3, paintB);
            canvas.drawLine(0, ly4, getWidth(), ly4, paintB);
            canvas.drawLine(0, ly5, getWidth(), ly5, paintG);
            canvas.drawLine(0, ly6, getWidth(), ly6, paintG);
            check();
            if (won) {
                obj.update(true);
                stop = true;
            }
            ly1 = allignlines(ly1);
            ly2 = allignlines(ly2);
            ly3 = allignlines(ly3);
            ly4 = allignlines(ly4);
            ly5 = allignlines(ly5);
            ly6 = allignlines(ly6);

            start = true;
            invalidate();
        }
    }

    private void Randomorder() {
        List<Float> solution = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            solution.add(arr[i]);
        }
        Collections.shuffle(solution);
        ly1 = solution.get(0);
        ly2 = solution.get(1);
        ly3 = solution.get(2);
        ly4 = solution.get(3);
        ly5 = solution.get(4);
        ly6 = solution.get(5);
        for(int i=0;i<6;i++)
        System.out.println(arr[i]);

        ly1 = allignlines(ly1);
        ly2 = allignlines(ly2);
        ly3 = allignlines(ly3);
        ly4 = allignlines(ly4);
        ly5 = allignlines(ly5);
        ly6 = allignlines(ly6);
    }

    private float allignlines(float ly) {

        if (ly <= WIDTH / 2) {
            ly = WIDTH / 2;
        }
        if (ly >= getHeight() - WIDTH / 2) {
            ly = getHeight() - WIDTH / 2;
        }
        return ly;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                if ((y > ly1 && y < ly1 + WIDTH / 2) || (y < ly1 && y > ly1 - WIDTH / 2) || y == ly1)
                    touchedly1 = true;
                else if ((y > ly2 && y < ly2 + WIDTH / 2) || (y < ly2 && y > ly2 - WIDTH / 2) || y == ly2)
                    touchedly2 = true;
                else if ((y > ly3 && y < ly3 + WIDTH / 2) || (y < ly3 && y > ly3 - WIDTH / 2) || y == ly3)
                    touchedly3 = true;
                else if ((y > ly4 && y < ly4 + WIDTH / 2) || (y < ly4 && y > ly4 - WIDTH / 2) || y == ly4)
                    touchedly4 = true;
                else if ((y > ly5 && y < ly5 + WIDTH / 2) || (y < ly5 && y > ly5 - WIDTH / 2) || y == ly5)
                    touchedly5 = true;
                else if ((y > ly6 && y < ly6 + WIDTH / 2) || (y < ly6 && y > ly6 - WIDTH / 2) || y == ly6)
                    touchedly6 = true;
            }
            break;
            case MotionEvent.ACTION_UP: {
                touchedly1 = false;
                touchedly2 = false;
                touchedly3 = false;
                touchedly4 = false;
                touchedly5 = false;
                touchedly6 = false;
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if (touchedly1)
                    ly1 = y;
                else if (touchedly2)
                    ly2 = y;
                else if (touchedly3)
                    ly3 = y;
                else if (touchedly4)
                    ly4 = y;
                else if (touchedly5)
                    ly5 = y;
                else if (touchedly6)
                    ly6 = y;
            }
            break;
        }
        invalidate();
        return true;
    }

    private void check() {

           if(!(touchedly1||touchedly2||touchedly3||touchedly4||touchedly5||touchedly6)) {
               won = true;
               if ((ly1 < ly3 && ly3 < ly2) || (ly1 < ly4 && ly4 < ly2) || (ly1 < ly5 && ly5 < ly2) || (ly1 < ly6 && ly6 < ly2) || (ly1 > ly3 && ly3 > ly2) || (ly1 > ly4 && ly4 > ly2) || (ly1 > ly5 && ly5 > ly2) || (ly1 > ly6 && ly6 > ly2)) {
                   won = false;
               }
               if ((ly3 < ly1 && ly1 < ly4) || (ly3 < ly2 && ly2 < ly4) || (ly3 < ly5 && ly5 < ly4) || (ly3 < ly6 && ly6 < ly4) || (ly3 > ly1 && ly1 > ly4) || (ly3 > ly2 && ly2 > ly4) || (ly3 > ly5 && ly5 > ly4) || (ly3 > ly6 && ly6 > ly4)) {
                   won = false;

               }
               if ((ly5 < ly1 && ly1 < ly6) || (ly5 < ly2 && ly2 < ly6) || (ly5 < ly3 && ly3 < ly6) || (ly5 < ly4 && ly4 < ly6) || (ly5 > ly1 && ly1 > ly6) || (ly5 > ly2 && ly2 > ly6) || (ly5 > ly3 && ly3 > ly6) || (ly5 > ly4 && ly4 > ly6)) {
                   won = false;
               }
           }
        }
        public interface transfer
        {
            void update(boolean won);
        }
        public void settransfer(transfer obj)
        {
            this.obj = obj;
        }

    }

