package com.android.productdetails.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.productdetails.R;
import com.android.productdetails.base.BaseFragment;
import com.android.productdetails.utils.TextUtils;
import com.squareup.picasso.Picasso;

public class ProductFragment extends BaseFragment {

    private final static String IMAGE = "com.android.productdetails.product.IMAGE";
    private View view;
    private ImageView imgProduct;

    public static ProductFragment newInstance(String imageUrl) {
        ProductFragment productFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE, imageUrl);
        productFragment.setArguments(bundle);
        return productFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_product, container, false);
            init(view);
            setupDefaults();
            setupEvents();
        }
        return view;
    }

    private void init(View view) {
        imgProduct = (ImageView) view.findViewById(R.id.product_image);
    }

    private void setupDefaults() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String url = bundle.getString(IMAGE);
            if (!TextUtils.isNullOrEmpty(url)) {
                Picasso.with(getActivity())
                        .load(url)
                        .into(imgProduct);
            }
        }
    }

    private void setupEvents() {

    }
}
