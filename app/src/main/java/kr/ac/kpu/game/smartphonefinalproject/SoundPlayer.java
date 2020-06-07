package kr.ac.kpu.game.smartphonefinalproject;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int getPointSound;
    private static int hitBlackSound;
    private final int SOUND_POOL_MAX = 2;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        //소리 로드
        getPointSound = soundPool.load(context, R.raw.hit,1);
        hitBlackSound = soundPool.load(context, R.raw.over , 1);
    }
    public static void playGetPointSound() {

        soundPool.play(getPointSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public static void playHitBlackSound() {
        soundPool.play(hitBlackSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
