package kr.ac.kpu.game.smartphonefinalproject;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int getPointSound;
    private static int hitBlackSound;
    private final int SOUND_POOL_MAX = 2;
    private static MediaPlayer mediaPlayer;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        //소리 로드
        getPointSound = soundPool.load(context, R.raw.hit,1);
        hitBlackSound = soundPool.load(context, R.raw.over , 1);

        //BGM
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.alone);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.7f, 0.7f);
    }
    public static void playGetPointSound() {

        soundPool.play(getPointSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public static void playHitBlackSound() {
        soundPool.play(hitBlackSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playBGM() {
        if (mediaPlayer != null) mediaPlayer.start();
    }
    public static void pauseBGM() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }
    public static void seekToTop(){
        if (mediaPlayer != null) mediaPlayer.seekTo(0);
    }

}
