package com.example.nguyenvantuan.testapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by nguyenvantuan on 7/15/15.
 */
public class Util {

    public static void savePassCode(Context context, String passCode) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(Constant.KEY_MASTER, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.KEY_PASS_CODE, passCode);
        editor.commit();
    }

    public static String getPassCode(Context context) {
        SharedPreferences savedSession =
                context.getSharedPreferences(Constant.KEY_MASTER, Context.MODE_PRIVATE);
        return savedSession.getString(Constant.KEY_PASS_CODE, "");
    }

    public static AlertDialog createDialog(Context context, String title, String message,
                                                       boolean hasTitle, DialogInterface.OnClickListener listener) {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);

        if (hasTitle) {
            alertDialogBuilder.setTitle(title);
        }
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", listener);
        return alertDialogBuilder.create();
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dimension);
        return dimension.widthPixels;
    }

    public static int convertDPToPixels(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

}
