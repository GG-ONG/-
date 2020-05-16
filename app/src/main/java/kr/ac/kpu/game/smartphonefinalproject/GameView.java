package kr.ac.kpu.game.smartphonefinalproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class GameView extends View {

    //kpu로고
    private Bitmap kpu;

    //배경
    private Bitmap bgImage;

    //점수
    private Paint scorePaint = new Paint();

    //레벨
    private Paint levelPaint = new Paint();

    //생명
    private Bitmap life[] = new Bitmap[2];

    public GameView(Context context) {
        super(context);

        kpu = BitmapFactory.decodeResource(getResources(), R.drawable.kpu);

        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.campus);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bgImage, 0, 0, null);
        canvas.drawBitmap(kpu, 0, 0, null);
        canvas.drawText("Score : 0", 20, 60, scorePaint);
        canvas.drawText("Lv.1", getWidth() /2f ,70, levelPaint);

        canvas.drawBitmap(life[0], 600, 30, null);
        canvas.drawBitmap(life[0], 660, 30, null);
        canvas.drawBitmap(life[1], 720, 30, null);

    }
}
