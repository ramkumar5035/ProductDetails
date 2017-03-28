package com.android.productdetails.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ramkumarc on 3/27/2017.
 */
public class JsonUtils {
    public static String loadJSONFromAsset(Context context, String fileName) {
        if (TextUtils.isNullOrEmpty(fileName)) return null;

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
