package com.project.mobileonline.utils;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.models.Constants;

import java.util.Arrays;

import static com.project.mobileonline.models.Constants.ALL_PRODUCT;
import static com.project.mobileonline.models.Constants.CART_ODER_ID;
import static com.project.mobileonline.models.Constants.CONTENT;
import static com.project.mobileonline.models.Constants.CREATED_AT;
import static com.project.mobileonline.models.Constants.HIGH_PRODUCT;
import static com.project.mobileonline.models.Constants.LOW_PRODUCT;
import static com.project.mobileonline.models.Constants.MEDIUM_PRODUCT;
import static com.project.mobileonline.models.Constants.NEWS_IMAGE;
import static com.project.mobileonline.models.Constants.NEWS_TABLE;
import static com.project.mobileonline.models.Constants.NORMAL_PRODUCT;
import static com.project.mobileonline.models.Constants.NUMBER_STAR;
import static com.project.mobileonline.models.Constants.ORDER_TABLE;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_TABLE;
import static com.project.mobileonline.models.Constants.PRODUCT_TYPE;
import static com.project.mobileonline.models.Constants.RECENT_PRODUCT;
import static com.project.mobileonline.models.Constants.PRODUCT_THUMBNAIL_IMAGE;
import static com.project.mobileonline.models.Constants.TITLE;

/**
 * Created by nguyendinhduc on 10/29/15.
 */
public class ParseHelper {
    public ParseHelper() {
    }

    public ParseQuery<ParseObject> getProductQuery(int query, int productType) {
        ParseQuery<ParseObject> productQuery = ParseQuery.getQuery(PRODUCT_TABLE);
        productQuery.orderByDescending(CREATED_AT);
        productQuery.whereEqualTo(PRODUCT_TYPE, productType);
        switch (query) {
            case RECENT_PRODUCT:
                productQuery.setLimit(3);
                break;
            case HIGH_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereGreaterThan(PRODUCT_PRICE, 8000000);
                break;
            case MEDIUM_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereLessThan(PRODUCT_PRICE, 8000000);
                productQuery.whereGreaterThan(PRODUCT_PRICE, 4000000);
                break;
            case LOW_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereLessThan(PRODUCT_PRICE, 4000000);
                break;
            case ALL_PRODUCT:
                break;
        }
        return productQuery;
    }

    public ParseQuery<ParseObject> getBestsellerQuery() {

        return ParseQuery.getQuery(Constants.BESTSELLER_TABLE).orderByDescending(CREATED_AT);
    }

    public ParseQuery<ParseObject> getNewsQuery() {
        ParseQuery<ParseObject> query = new ParseQuery<>(NEWS_TABLE);
        query.orderByDescending(CREATED_AT);
        return query;
    }

    public ParseQuery<ParseObject> getHistoryOrderQuery() {
        ParseQuery<ParseObject> query = new ParseQuery<>(ORDER_TABLE);
        query.orderByDescending(CREATED_AT);
        return query;
    }

    public ParseQuery<ParseObject> getOrderedProductList(String orderId) {
        ParseQuery<ParseObject> query = new ParseQuery<>(CART_ODER_ID);
        query.whereEqualTo(CART_ODER_ID, orderId);
        return query;
    }
}
