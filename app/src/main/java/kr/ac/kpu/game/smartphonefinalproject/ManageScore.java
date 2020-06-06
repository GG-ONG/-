package kr.ac.kpu.game.smartphonefinalproject;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageScore {
    private static Context context;

    public ManageScore(Context context){
        ManageScore.context = context;
    }
    public void saveScore(int score){
        SharedPreferences setting = context.getSharedPreferences("GAME_DATE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt("HIGH_SCORE", score);
        editor.commit();
    }
    public int loadHighScore(){
        SharedPreferences settings = context.getSharedPreferences("GAME_DATE", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        return highScore;
    }
}
