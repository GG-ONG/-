package kr.ac.kpu.game.smartphonefinalproject;


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

    //검은볼
    private int blackX;
    private int blackY;
    private int blackSpeed = 50;
    //private Paint blackPaint = new Paint();
    private Bitmap blackPaint;

    //파랑볼
    private int blueX;
    private int blueY;
    private int blueSpeed = 70;
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
    private ManageScore manageScore;
    private int highScore;

    //레벨
    private Paint levelPaint = new Paint();


    //생명
    private Bitmap life[] = new Bitmap[2];
    private int life_count;

    //상태 체크
    private boolean touch_flg = false;
    private int gameState;
    private static final int GAME_START = 0;
    private static final int GAME_PLAY = 1;
    private static final int GAME_OVER = 2;

    //사운드
    SoundPlayer soundPlayer;

    // 시작, 게임오버
    private Bitmap startImage;
    private Bitmap gameOverImage;
    private Bitmap startBtn;
    private Bitmap returnBtn;
    private int btnImageX;
    private int btnImageY;
    private Paint titleScorePaint = new Paint();

    public GameView(Context context) {
        super(context);

        kpu[0] = BitmapFactory.decodeResource(getResources(), R.drawable.kpu1);
        kpu[1] = BitmapFactory.decodeResource(getResources(), R.drawable.kpu2);



        startImage = BitmapFactory.decodeResource(getResources(), R.drawable.start);
        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.campus);
        gameOverImage = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        //blackPaint.setColor(Color.BLACK);
        //blackPaint.setAntiAlias(false);
        blackPaint = BitmapFactory.decodeResource(getResources(), R.drawable.f);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setTextSize(50);



        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        startBtn = BitmapFactory.decodeResource(getResources(), R.drawable.start_btn);
        returnBtn = BitmapFactory.decodeResource(getResources(),R.drawable.return_btn);

        titleScorePaint.setTextSize(70);
        titleScorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        titleScorePaint.setTextAlign(Paint.Align.CENTER);


        gameState = GAME_START;

        manageScore = new ManageScore(context);
        highScore = manageScore.loadHighScore();

        //사운드
        soundPlayer = new SoundPlayer(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = getWidth();
        canvasHeight = getHeight();

        btnImageX = canvasWidth / 2 - startBtn.getWidth() / 2;
        btnImageY = canvasHeight / 2 + (int)(startBtn.getHeight() * 1.5);

        switch (gameState){
            case GAME_START:
                canvas.drawBitmap(startImage, 0, 0, null);
                drawStart(canvas);
                break;
            case GAME_PLAY:
                canvas.drawBitmap(bgImage, 0,0,null);
                drawPlay(canvas);
                break;
            case GAME_OVER:
                canvas.drawBitmap(gameOverImage, 0, 0, null);
                drawOver(canvas);
                break;
        }


    }
    public void drawPlay(Canvas canvas){

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

        //검은색
        blackX -= blackSpeed;
        if(hitCheck(blackX, blackY)){
            blackX = -100;
            life_count--;
            soundPlayer.playHitBlackSound();
            if(life_count == 0){
                soundPlayer.pauseBGM();
                if(score > highScore){
                    manageScore.saveScore(score);
                    highScore = score;
                }
                gameState = GAME_OVER;
                return;
            }
        }
        if (blackX < 0){
            blackX = canvasWidth +200;
            blackY = (int) Math.floor(Math.random() * (maxKpuY - minKpuY)) + minKpuY;
        }
        canvas.drawBitmap(blackPaint, blackX, blackY,null);

        //파랑
        blueX -= blueSpeed;
        if(hitCheck(blueX, blueY)){
            score +=10;
            blueX = -100;
            soundPlayer.playGetPointSound();

        }
        if(blueX < 0){
            blueX = canvasWidth + 20;
            blueY = (int) Math.floor(Math.random() * (maxKpuY - minKpuY)) + minKpuY;
        }
        canvas.drawCircle(blueX, blueY, 10, bluePaint);

        int level = (int) Math.floor(score / 50) +1 ;

        //점수
        canvas.drawText("Score : "+ score, 20, 60, scorePaint);

        //레벨
        canvas.drawText("레벨 : " + level, canvasWidth / 3, 70, levelPaint);


        //생명
        for (int i = 0; i <3; i++){
            int x = (int) (600+ life[0].getWidth() * 1.5 *i);
            int y = 30;

            if(i<life_count){
                canvas.drawBitmap(life[0], x, y, null);
            } else{
                canvas.drawBitmap(life[1], x, y, null);

            }
        }

    }
    public void drawStart(Canvas canvas){
        //처음 위치, 점수, 생명 초기화
        KpuY = canvasHeight / 2;
        blueX = -100;
        blackX = -100;
        score = 0;
        life_count = 3;

        canvas.drawText("High Score : " + highScore, canvasWidth /2, canvasHeight /2, titleScorePaint);
        canvas.drawBitmap(startBtn, btnImageX, btnImageY, null);

    }
    public void drawOver(Canvas canvas){
        canvas.drawText("Score : " + score, canvasWidth / 2, canvasHeight /2 , titleScorePaint);
        canvas.drawBitmap(returnBtn, btnImageX, btnImageY,null);

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
            switch(gameState){
                case GAME_START:
                    if(buttonTapCheck(startBtn, (int)event.getX(), (int)event.getY())){
                        soundPlayer.seekToTop();
                        soundPlayer.playBGM();
                        gameState = GAME_PLAY;
                    }
                    break;
                case GAME_PLAY:
                    touch_flg = true;
                    KpuSpeed = -20;
                    break;
                case GAME_OVER:
                    if(buttonTapCheck(returnBtn, (int)event.getX(), (int)event.getY())){
                        gameState = GAME_START;
                    }
                    break;

            }

        }
        return true;
    }
    public boolean buttonTapCheck(Bitmap button, int x, int y){
        if(btnImageX < x && x <btnImageX + button.getWidth() && btnImageY < y && y < btnImageY
        + button.getHeight()){
            return true;
        }
        return false;
    }
    public int getGameState(){
        return gameState;
    }
}
