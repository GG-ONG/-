package kr.ac.kpu.game.smartphonefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private Handler handler = new Handler();
    private final static long TiMER_INTERVAL = 50;

    private InterstitialAd interstitialAd;




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


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/8691691433");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gameView.getGameState() == 1) SoundPlayer.playBGM();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundPlayer.pauseBGM();
    }
}
