package com.scode.shaketest;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by 知らないのセカイ on 2017/6/11.
 */

public class VibrateUtil {
    public static void Vibrate(Activity activity, long milliseconds) {
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);//获取vibrator
        vibrator.vibrate(milliseconds);//设置震动的时间单位为毫秒
    }

    public static void Vibrate(Activity activity, long[] pattern, boolean isRepeat) {
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, isRepeat ? 1 : -1);//设置震动的静止的时长然后震动的时长再静止时长。。。接着设置了是否循环
    }

}
