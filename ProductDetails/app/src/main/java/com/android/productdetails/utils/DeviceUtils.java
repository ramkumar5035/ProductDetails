package com.android.productdetails.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class DeviceUtils {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getDensityDpi(Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showSoftKeyboard(Dialog paramDialog) {
        paramDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public static void showSoftKeyboard(Context context, View paramView) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(paramView,
                InputMethodManager.SHOW_IMPLICIT);
    }

    public static int getPixelFromDp(Context context, int dpUnits) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpUnits, getDisplayMetrics(context));
        return (int) px;
    }

    public static float getPixelFromDp(Context context, float dpInFloat) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpInFloat, getDisplayMetrics(context));
        return px;
    }

    public static float getFontPixelFromSp(Context context, float spUnits) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spUnits, getDisplayMetrics(context));
        return px;
    }

    /**
     * @param view
     * @param delay (Note: delay should be more than 100)
     */
    public static void showSoftInput(final Context context, final EditText view, int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(context, view);
            }
        }, delay);
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean hasKitkat() {
        return getSDKVersion() >= VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return getSDKVersion() >= VERSION_CODES.LOLLIPOP;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
