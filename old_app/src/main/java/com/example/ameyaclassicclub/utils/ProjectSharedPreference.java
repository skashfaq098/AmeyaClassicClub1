package com.example.ameyaclassicclub.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by rahulu on 2/16/2016.
 */
public class ProjectSharedPreference {
    @SuppressLint("StaticFieldLeak")
    private static ProjectSharedPreference mLernerSharedPreference = null;
    private final SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private ProjectSharedPreference(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
    }


    public static ProjectSharedPreference getInstance(Context context) {
        if (mLernerSharedPreference == null) {
            mLernerSharedPreference = new ProjectSharedPreference(context);
        }
        return mLernerSharedPreference;
    }

    // for fetching data from shared pref.
    public String fetchStringPreference(String key, String defaultValue) {
        String value = sharedPref.getString(key, "");
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }

    // for save data in to shared perf.
    public void saveStringPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    // for fetching data from shared pref.
    public boolean fetchBooleanPreference(String key, Boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveBooleanPreference(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    // for save long data in to shared perf.
    public void saveLongPreference(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long fetchLongPreference(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    // for fetching data from shared pref.
    public int fetchIntegerPreference(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    // for save data in to shared perf.
    public void saveIntegerPreference(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void removeFromSharedPreference(String key) {
        editor.remove(key);
        editor.commit();
    }

//    public void saveObjectPreference(String key, Object obj) {
//        editor.putString(key, new Gson().toJson(obj));
//        editor.commit();
//    }
//
//    public Object fetchObjectPreference(String key, Class<?> class1) {
//        return new Gson().fromJson(sharedPref.getString(key, ""), class1);
//    }

    public void clearAllData() {
        editor = sharedPref.edit().clear();
        editor.apply();
    }
}
