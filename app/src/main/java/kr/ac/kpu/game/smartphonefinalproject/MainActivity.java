package kr.ac.kpu.game.smartphonefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private Handler handler = new Handler();
    private final static long TiMER_INTERVAL = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        gameView = new GameView(this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0, TiMER_INTERVAL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gameView.getGameState() == 1) gameView.soundPlayer.playBGM();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.soundPlayer.pauseBGM();
    }
}
