package com.example.eventmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsForLogin {

        static final String IS_USER_LOGGED_IN = "isUserLoggedIn";

        static SharedPreferences getSharedPreferences(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        }

        public static void setUserLoggedIn(Context context, boolean isLoggedIn)
        {
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putBoolean(IS_USER_LOGGED_IN, isLoggedIn);
            editor.apply();
        }

        public static boolean isUserLoggedIn(Context context)
        {
            return getSharedPreferences(context).getBoolean(IS_USER_LOGGED_IN, false);
        }
    }

