package com.debut.androidfeatures;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shivam on 08/August/19.
 */

public class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private boolean isNightModeEnabled = false;
    public static final String MyPREFERENCES = "MyPrefs" ;

    private static InitApplication singleton = null;

    public static InitApplication getInstance() {
        if(singleton == null) {
            singleton = new InitApplication();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        SharedPreferences mPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        this.isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;
        SharedPreferences mPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }
}
