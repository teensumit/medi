package com.cheetah.setu.main;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Func {
    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
    public static void initShared(Context context){
        if (Vars.sharedPreferences==null)
            Vars.sharedPreferences = context.getSharedPreferences(Vars.MY_PREF, Context.MODE_PRIVATE);
    }
}
