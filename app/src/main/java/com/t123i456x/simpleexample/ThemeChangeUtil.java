package com.t123i456x.simpleexample;

import android.app.Activity;

public class ThemeChangeUtil {
    public static boolean isChange = false;
    public static void changeTheme(Activity activity){
        if(isChange){
            activity.setTheme(R.style.NightTheme);
        }
    }
}

