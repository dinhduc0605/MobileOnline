package com.project.mobileonline.utils;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Arrays;

import static com.project.mobileonline.models.Constants.ALL_PRODUCT;
import static com.project.mobileonline.models.Constants.CREATED_AT;
import static com.project.mobileonline.models.Constants.HIGH_PRODUCT;
import static com.project.mobileonline.models.Constants.LOW_PRODUCT;
import static com.project.mobileonline.models.Constants.MEDIUM_PRODUCT;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_TABLE;
import static com.project.mobileonline.models.Constants.RECENT_PRODUCT;
import static com.project.mobileonline.models.Constants.THUMBNAIL_IMAGE;

/**
 * Created by nguyendinhduc on 10/29/15.
 */
public class ParseHelper {
    public ParseHelper() {
    }

    public ParseQuery<ParseObject> getProductQuery(int query) {
        ParseQuery<ParseObject> productQuery = ParseQuery.getQuery(PRODUCT_TABLE);
        productQuery.orderByDescending(CREATED_AT);

        productQuery.selectKeys(Arrays.asList(THUMBNAIL_IMAGE, PRODUCT_NAME, PRODUCT_PRICE));
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

//    public ParseQuery<ParseObject> getBestseller() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery()
//    }
}
