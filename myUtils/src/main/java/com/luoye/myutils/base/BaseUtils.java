package com.luoye.myutils.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by: ls
 * TIME：2022/1/11
 * user：
 */
public class BaseUtils {


    private final static String TAG = "---Utils";

    public static void startActivity(Context context, Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    public static void startActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        if (bundle != null) intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /*设置显示的Fragment*/
    public static FragmentTransaction switchFragment(AppCompatActivity activity, int containerViewId, Fragment targetFragment) {
        FragmentManager fm = activity.getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        FragmentTransaction transaction = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (!targetFragment.equals(fragment) && !fragment.isHidden())
                transaction.hide(fragment);
        }
        //判断是否是第一次添加
        if (targetFragment.isAdded()) {
            //隐藏当前页面 显示需要显示的页面
            transaction.show(targetFragment);
        } else {
            //添加新的页面
            transaction.add(containerViewId, targetFragment, targetFragment.getClass().getName());
        }
        return transaction;
    }

    /*设置显示的Fragment*/
    public static FragmentTransaction switchFragment(FragmentActivity activity, int containerViewId, Fragment targetFragment) {
        FragmentManager fm = activity.getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        FragmentTransaction transaction = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (!targetFragment.equals(fragment) && !fragment.isHidden())
                transaction.hide(fragment);
        }
        //判断是否是第一次添加
        if (targetFragment.isAdded()) {
            //隐藏当前页面 显示需要显示的页面
            transaction.show(targetFragment);
        } else {
            //添加新的页面
            transaction.add(containerViewId, targetFragment, targetFragment.getClass().getName());
        }
        return transaction;
    }


    public static AlertDialog.Builder getNormalDialog(Context context, String title, String message, String pButName, DialogInterface.OnClickListener pOnClickListener, String nButName, DialogInterface.OnClickListener nOnClickListener) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton(pButName, pOnClickListener);
        normalDialog.setNegativeButton(nButName, nOnClickListener);
        return normalDialog;
    }

    public static void showNormalDialog(Context context, String title, String message, String pButName, DialogInterface.OnClickListener pOnClickListener, String nButName, DialogInterface.OnClickListener nOnClickListener) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton(pButName, pOnClickListener);
        normalDialog.setNegativeButton(nButName, nOnClickListener);
        normalDialog.show();
    }

    /*状态栏颜色*/
    public static void winTextColorWhite(Context context, String bgColor) {
        ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        ((Activity) context).getWindow().setStatusBarColor(Color.parseColor(bgColor));
    }

    /*状态栏颜色*/
    public static void winTextColorBlack(Context context, String bgColor) {
        ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ((Activity) context).getWindow().setStatusBarColor(Color.parseColor(bgColor));
    }

    /*全屏*/
    public static void winXY(Context context) {
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        ((Activity) context).getWindow().setAttributes(params);
        ((Activity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /*获取屏幕大小*/
    public static int[] getScreenXY(Context context) {
        int[] xY = new int[2];
        xY[0] = context.getResources().getDisplayMetrics().widthPixels;
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        xY[1] = displayMetrics.heightPixels;
        return xY;
    }

    /*float保留几位小数*/
    public static String floatFormat(float value, int size) {
        BigDecimal text = new BigDecimal(value);
        return text.setScale(size, BigDecimal.ROUND_HALF_DOWN).toString();
    }

    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }


    private static ProgressDialog progressDialog;

    /**
     * 显示进度对话框，还可以设置标题等等
     */
    public static void showProgressDialog(Context context, String message) {
        if (progressDialog != null) progressDialog.dismiss();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog() {
        if (progressDialog != null) progressDialog.dismiss();
    }


    /*防止重复显示*/
    private static Toast toast;

    @SuppressLint("ShowToast")
    private static void makeText(Context context, String msg, int duration) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    public static void makeShortText(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static void makeLongText(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_LONG);
    }

    /*防止连续点击*/
    private static long time = 0;

    public static boolean isOnClick(int ms) {
        boolean is = System.currentTimeMillis() - time > ms;
        if (is) {
            time = System.currentTimeMillis();
            Log.i(TAG, "触发点击 ms:" + ms);
        } else {
            Log.i(TAG, "本次禁止");
        }
        return is;
    }

}
