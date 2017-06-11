package com.scode.shaketest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 知らないのセカイ on 2017/6/11.
 */

public class ToastUtil {
    private static  Toast toast = null;
    public static Toast getToast(Context context,CharSequence text,int time){
        if (toast != null) {
            toast.setText(text);
            toast.setDuration(time);
            return toast;
        }
      synchronized (ToastUtil.class){
          if (toast == null) {
              toast = Toast.makeText(context, text, time);
              return toast;
          }
      }
      return null;
    }

}
