package humazed.github.com.autismkidsgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import humazed.github.com.autismkidsgame.puzzle.dish.DishManager;
import humazed.github.com.autismkidsgame.puzzle.utils.StaticValue;
import humazed.github.com.kotlinandroidutils.LineNumberDebugTree;

import static humazed.github.com.kotlinandroidutils.TimberKtKt.initTimber;


/**
 * Puzzle
 * Created by ZQ on 2016/3/21.
 */
public class App extends MultiDexApplication {

    private static Context mContext;

    private static DishManager dm;
    private static int level = 4;

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        if (level < 3) return;
        App.level = level;
        initDishManager();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static void initDishManager() {
        dm = new DishManager(level);
    }

    public static DishManager getDishManager() {
        return dm;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber(BuildConfig.DEBUG, null, new LineNumberDebugTree());

        if (mContext == null) mContext = getApplicationContext();
        SharedPreferences pref = getSharedPreferences(StaticValue.SP_NAME, MODE_PRIVATE);
        setLevel(pref.getInt(StaticValue.SP_LEVEL, 3));
        changeLevel();
    }

    private void changeLevel() {
        int level = 3;
        SharedPreferences.Editor editor = getSharedPreferences(StaticValue.SP_NAME, MODE_PRIVATE).edit();
        editor.putInt(StaticValue.SP_LEVEL, level);
        editor.apply();
        App.setLevel(level);
    }

}
