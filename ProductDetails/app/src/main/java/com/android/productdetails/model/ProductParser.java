package com.android.productdetails.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ramkumarc on 3/27/2017.
 */
public class ProductParser implements Serializable {
    public int success;
    public Response response;

    public class Response {
        ProductList product_list;
        List<CompareProduct> compare_products;
        List<Object> related_products;

        public ProductList getProduct_list() {
            return product_list;
        }

        public void setProduct_list(ProductList product_list) {
            this.product_list = product_list;
        }

        public List<CompareProduct> getCompare_products() {
            return compare_products;
        }

        public void setCompare_products(List<CompareProduct> compare_products) {
            this.compare_products = compare_products;
        }

        public List<Object> getRelated_products() {
            return related_products;
        }

        public void setRelated_products(List<Object> related_products) {
            this.related_products = related_products;
        }
    }
}
