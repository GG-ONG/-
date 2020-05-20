package kr.ac.kpu.game.smartphonefinalproject;

import android.bluetooth.BluetoothA2dp;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class GameView extends View {

    //캔버스
    private int canvasWidth;
    private int canvasHeight;

    //파랑볼
    private int blueX;
    private int blueY;
    private int blueSpeed = 30;
    private Paint bluePaint = new Paint();

    //kpu로고
    private Bitmap kpu[] = new Bitmap[2];
    private int KpuX=10;
    private int KpuY;
    private int KpuSpeed;

    //배경
    private Bitmap bgImage;

    //점수
    private Paint scorePaint = new Paint();
    private int score;

    //레벨
    private Paint levelPaint = new Paint();

    //생명
    private Bitmap life[] = new Bitmap[2];

    //상태 체크
    private boolean touch_flg = false;

    public GameView(Context context) {
        super(context);

        kpu[0] = BitmapFactory.decodeResource(getResources(), R.drawable.kpu1);
        kpu[1] = BitmapFactory.decodeResource(getResources(), R.drawable.kpu2);


        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.campus);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        //처음 위치
        KpuY = 500;
        score = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bgImage, 0, 0, null);
        //Kpu
        int minKpuY = kpu[0].getHeight();
        int maxKpuY = canvasHeight - kpu[0].getHeight() * 3;

        KpuY += KpuSpeed;
        if (KpuY < minKpuY) KpuY = minKpuY;
        if (KpuY > maxKpuY) KpuY = maxKpuY;
        KpuSpeed += 2;

        if(touch_flg){
            // 날개
            canvas.drawBitmap(kpu[1], KpuX, KpuY, null);
            touch_flg = false;
        } else{
            canvas.drawBitmap(kpu[0], KpuX, KpuY, null);
        }

        //파랑
        blueX -= blueSpeed;
        if(hitCheck(blueX, blueY)){
            score +=10;
            blueX = -100;

        }
        if(blueX < 0){
            blueX = canvasWidth + 20;
            blueY = (int) Math.floor(Math.random() * (maxKpuY - minKpuY)) + minKpuY;
        }
        canvas.drawCircle(blueX, blueY, 10, bluePaint);

        canvas.drawText("Score : "+ score, 20, 60, scorePaint);
        canvas.drawText("Lv.1", canvasWidth /2f ,70, levelPaint);

        canvas.drawBitmap(life[0], 600, 30, null);
        canvas.drawBitmap(life[0], 660, 30, null);
        canvas.drawBitmap(life[1], 720, 30, null);


    }

    public boolean hitCheck(int x, int y) {
        if(KpuX < x && x < (KpuX + kpu[0].getWidth()) &&
                KpuY < y && y < (KpuY + kpu[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_flg = true;
            KpuSpeed = -20;
        }
        return true;
    }
}
