package com.android.productdetails.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.productdetails.PDApp;

public class BaseFragment extends Fragment {

    public PDApp getApp() {
        return (PDApp) getActivity().getApplication();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void finish() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).finish();
        }
    }
}
