package com.android.productdetails.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.productdetails.PDApp;
import com.android.productdetails.utils.DeviceUtils;

public class BaseActivity extends AppCompatActivity {

    public PDApp getApp() {
        return (PDApp) getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void finish() {
        DeviceUtils.hideSoftKeyboard(this);
        super.finish();
    }

    @Override
    public void finishAffinity() {
        DeviceUtils.hideSoftKeyboard(this);
        super.finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
