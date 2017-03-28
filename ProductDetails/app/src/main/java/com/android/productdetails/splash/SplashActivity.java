package com.android.productdetails.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.productdetails.R;
import com.android.productdetails.base.BaseActivity;
import com.android.productdetails.product.ProductDetailsActivity;

public class SplashActivity extends BaseActivity {
    private static final long SLEEP_DURATION = 2000l;
    private boolean isDestroyed = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1 && !isDestroyed) {
                Intent intent = new Intent(SplashActivity.this, ProductDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(1, SLEEP_DURATION);
    }

    @Override
    protected void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }
}
