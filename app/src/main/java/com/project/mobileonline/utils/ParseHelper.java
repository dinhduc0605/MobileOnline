package com.project.mobileonline.utils;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.models.Constants;

import static com.project.mobileonline.models.Constants.ALL_PRODUCT;
import static com.project.mobileonline.models.Constants.BANNER_TABLE;
import static com.project.mobileonline.models.Constants.BESTSELLER_PRODUCT_ID;
import static com.project.mobileonline.models.Constants.CART_ODER_ID;
import static com.project.mobileonline.models.Constants.CART_PRODUCT_ID;
import static com.project.mobileonline.models.Constants.CREATED_AT;
import static com.project.mobileonline.models.Constants.HIGH_PRODUCT;
import static com.project.mobileonline.models.Constants.LOW_PRODUCT;
import static com.project.mobileonline.models.Constants.MEDIUM_PRODUCT;
import static com.project.mobileonline.models.Constants.NEWS_TABLE;
import static com.project.mobileonline.models.Constants.ORDER_TABLE;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_TABLE;
import static com.project.mobileonline.models.Constants.PRODUCT_TYPE;
import static com.project.mobileonline.models.Constants.RECENT_PRODUCT;
import static com.project.mobileonline.models.Constants.SHOPPING_CART_TABLE;

/**
 * Created by nguyendinhduc on 10/29/15.
 */
public class ParseHelper {
    public ParseHelper() {
    }

    public static ParseQuery<ParseObject> getProductQuery(int query, int productType) {
        ParseQuery<ParseObject> productQuery = ParseQuery.getQuery(PRODUCT_TABLE);
        productQuery.orderByDescending(CREATED_AT);
        productQuery.whereEqualTo(PRODUCT_TYPE, productType);
        switch (query) {
            case RECENT_PRODUCT:
                productQuery.setLimit(3);
                break;
            case HIGH_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereGreaterThan(PRODUCT_PRICE, 800);
                break;
            case MEDIUM_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereLessThan(PRODUCT_PRICE, 800);
                productQuery.whereGreaterThan(PRODUCT_PRICE, 400);
                break;
            case LOW_PRODUCT:
                productQuery.setLimit(3);
                productQuery.whereLessThan(PRODUCT_PRICE, 400);
                break;
            case ALL_PRODUCT:
                break;
        }
        return productQuery;
    }

    public static ParseQuery<ParseObject> getBestsellerQuery() {

        return ParseQuery.getQuery(Constants.BESTSELLER_TABLE)
                .orderByDescending(CREATED_AT)
                .include(BESTSELLER_PRODUCT_ID);
    }

    public static ParseQuery<ParseObject> getNewsQuery() {
        ParseQuery<ParseObject> query = new ParseQuery<>(NEWS_TABLE);
        query.orderByDescending(CREATED_AT);
        return query;
    }

    public static ParseQuery<ParseObject> getHistoryOrderQuery() {
        ParseQuery<ParseObject> query = new ParseQuery<>(ORDER_TABLE);
        query.orderByDescending(CREATED_AT);
        return query;
    }

    public static ParseQuery<ParseObject> getOrderedProductList(ParseObject order) {
        ParseQuery<ParseObject> query = new ParseQuery<>(SHOPPING_CART_TABLE);
        query.whereEqualTo(CART_ODER_ID, order);
        query.orderByDescending(CREATED_AT);
        query.include(CART_PRODUCT_ID);
        return query;
    }

    public static ParseQuery<ParseObject> getOrderedProductLocal() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(SHOPPING_CART_TABLE);
        query.fromLocalDatastore();
        query.orderByDescending(CREATED_AT);
        return query;
    }

    public static ParseQuery<ParseObject> getBannerImage() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(BANNER_TABLE);
        query.orderByDescending(CREATED_AT);
        return query;
    }
}
