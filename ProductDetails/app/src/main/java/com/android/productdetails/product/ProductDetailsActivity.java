package com.android.productdetails.product;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.productdetails.R;
import com.android.productdetails.base.BaseActivity;
import com.android.productdetails.model.ProductList;
import com.android.productdetails.model.ProductParser;
import com.android.productdetails.utils.JsonUtils;
import com.android.productdetails.utils.TextUtils;
import com.android.productdetails.views.PDTextView;
import com.android.productdetails.views.pager_indicator.CirclePageIndicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity {

    private ViewPager viewPager;
    private CirclePageIndicator pageIndicator;
    private ProductPagerAdapter pagerAdapter;
    private PDTextView txtProductName;
    private PDTextView txtProductPrice;
    private PDTextView txtProductDescription;
    private PDTextView btnShowMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initViews();
        setupDefaults();
        setupEvents();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.pager);
        pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        txtProductName = (PDTextView) findViewById(R.id.product_name);
        txtProductPrice = (PDTextView) findViewById(R.id.price);
        txtProductDescription = (PDTextView) findViewById(R.id.product_description);
        btnShowMore = (PDTextView) findViewById(R.id.show_more);
        btnShowMore.setTag(0);
    }

    private void setupDefaults() {
        String productJson = JsonUtils.loadJSONFromAsset(this, "product_details.json");
        if (!TextUtils.isNullOrEmpty(productJson)) {
            Gson gson = new GsonBuilder().create();
            ProductParser productParser = gson.fromJson(productJson, ProductParser.class);
            if (productParser != null && productParser.response != null) {
                ProductList list = productParser.response.getProduct_list();
                setupViewPager(list.getImage());
                updateUI(list);
            }
        }
    }

    private void setupEvents() {
        btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                if (tag == 0) {
                    v.setTag(1);
                    expandTextView(txtProductDescription);
                } else {
                    v.setTag(0);
                    collapseTextView(txtProductDescription, 3);
                }
            }
        });
    }

    private void setupViewPager(List<String> image) {
        if (image == null && image.size() == 0) return;

        ArrayList<ProductFragment> productFragments = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            productFragments.add(ProductFragment.newInstance(image.get(i)));
        }
        pagerAdapter = new ProductPagerAdapter(getSupportFragmentManager(), productFragments);
        viewPager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
    }

    private void updateUI(ProductList product) {
        if (product == null) return;

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(product.getName());

        txtProductName.setText(product.getName());
        txtProductPrice.setText(product.getPrice());

        Spanned spannedDescription;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spannedDescription = Html.fromHtml(product.getDescription(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            spannedDescription = Html.fromHtml(product.getDescription());
        }
        txtProductDescription.setText(spannedDescription);
        txtProductDescription.setMaxLines(txtProductDescription.getLineCount());
        collapseTextView(txtProductDescription, 3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void expandTextView(TextView tv) {
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", tv.getLineCount());
        animation.setDuration(200).start();
        btnShowMore.setText(getString(R.string.show_less));
    }

    private void collapseTextView(TextView tv, int numLines) {
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", numLines);
        animation.setDuration(200).start();
        btnShowMore.setText(getString(R.string.show_more));
    }
}
