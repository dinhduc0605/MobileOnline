package com.project.mobileonline.models;

import android.os.Environment;

/**
 * Created by Nguyen Dinh Duc on 9/16/2015.
 */
public class Constants {
    public static final int PICK_IMAGE_CODE = 1;
    public static final int PROFILE_ACTIVITY_REQUEST_CODE = 0;
    public static final String DIRECTORY_PATH = Environment.getExternalStorageDirectory() + "/Mobile Online/";
    public static final String AVATAR_IMAGE_NAME = "avatar.png";

    //user table
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phoneNumber";
    public static final String ADDRESS = "address";
    public static final String BIRTHDAY = "birthday";
    public static final String GENDER = "gender";
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String AVATAR = "avatar";

    //Product Categories
    public static final int RECENT_PRODUCT = 0;
    public static final int HIGH_PRODUCT = 1;
    public static final int MEDIUM_PRODUCT = 2;
    public static final int LOW_PRODUCT = 3;
    public static final int ALL_PRODUCT = 4;


    public static final String USER_TABLE = "_USER";

    //common column
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String OBJECT_ID = "objectId";

    //product table
    public static final String PRODUCT_TABLE = "Product";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_QUANTITY = "quantity";
    public static final String PRODUCT_MANUFACTURE = "manufacture";
    public static final String PRODUCT_SALE_PRICE = "salePrice";
    public static final String PRODUCT_OLD_PRICE = "oldPrice";
    public static final String TYPE = "type";
    public static final String THUMBNAIL_IMAGE = "thumbnailImage";
    public static final String PREVIEW_IMAGE = "previewImage";
    public static final String SPECIFICATION = "specification";

    //bestseller table
    public static final String BESTSELLER_TABLE = "Bestseller";
}
