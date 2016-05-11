package com.heightxdeveloper.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 12/28/2015.
 */
public class AppPrefrence {


    private static String MARRIAGE = "marriageprefrence";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MARRIAGE,
                Context.MODE_PRIVATE);
    }



    public static Boolean getIsLogin(Context ctx, String key) {
        return getSharedPreferences(ctx).getBoolean(key,false);
    }

    public static void setIsLogin(Context ctx, String key, Boolean value) {
        SharedPreferences.Editor mPrefsEditor = getSharedPreferences(ctx).edit();
        mPrefsEditor.putBoolean(key, value);
        mPrefsEditor.commit();
    }

    public static String getEmail(Context ctx, String key) {
        return getSharedPreferences(ctx).getString(key, null);
    }

    public static void setEmails(Context ctx, String key, String value) {
        SharedPreferences.Editor mPrefsEditor = getSharedPreferences(ctx).edit();
        mPrefsEditor.putString(key, value);
        mPrefsEditor.commit();
    }

    public static String getUserId(Context ctx, String key) {
        return getSharedPreferences(ctx).getString(key, null);
    }

    public static void setUserId(Context ctx, String key, String value) {
        SharedPreferences.Editor mPrefsEditor = getSharedPreferences(ctx).edit();
        mPrefsEditor.putString(key, value);
        mPrefsEditor.commit();
    }


}
