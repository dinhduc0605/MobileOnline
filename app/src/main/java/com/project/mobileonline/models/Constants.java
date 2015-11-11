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

    //Product Type
    public static final int NONE = 0;
    public static final int RECENT_PRODUCT = 1;
    public static final int HIGH_PRODUCT = 2;
    public static final int MEDIUM_PRODUCT = 3;
    public static final int LOW_PRODUCT = 4;
    public static final int ALL_PRODUCT = 5;

    //Product Categories
    public static final int NORMAL_PRODUCT = 1;
    public static final int SALE_OFF_PRODUCT = 2;
    public static final int OLD_PRODUCT = 3;

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
    public static final String PRODUCT_TYPE = "type";
    public static final String PRODUCT_THUMBNAIL_IMAGE = "thumbnailImage";
    public static final String PRODUCT_PREVIEW_IMAGE = "previewImage";
    public static final String SPECIFICATION = "specification";
    public static final String NUMBER_STAR = "numStar";
    public static final String SMALL_SLIDE_IMAGE = "smallSlideImage";
    public static final String LARGE_SLIDE_IMAGE = "largeSlideImage";
    public static final String BACKGROUND_IMAGE = "backgroundImage";
    public static final String PRODUCT_QUERY = "query";

    //bestseller table
    public static final String BESTSELLER_TABLE = "BestSeller";
    public static final String BESTSELLER_PRODUCT_ID = "productId";
    public static final String BESTSELLER_QUANTITY = "quantity";

    //news table
    public static final String NEWS_TABLE = "News";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String TAG = "tag";
    public static final String NEWS_IMAGE = "imageUrl";

    //order table
    public static final String ORDER_TABLE = "Order";
    public static final String USER_ID = "userId";
    public static final String ORDER_STATUS = "status";
    public static final String TOTAL_AMOUNT = "totalAmount";
    public static final String NOTE = "note";
    public static final String CURRENT_ORDER = "currentOder";
    public static final String ORDER_QUANTITY = "quantity";
    public static final String ORDER_TYPE = "type";
    public static final String ORDER_CONTENT = "content";
    public static final int TYPE_SHIP = 1;
    public static final int TYPE_STORE = 2;
    public static final int STATUS_DONE = 1;
    public static final int STATUS_PROGRESS = 2;
    public static final int STATUS_ABORT = 3;

    //shoping cart table
    public static final String SHOPPING_CART_TABLE = "ShoppingCart";
    public static final String CART_PRODUCT_ID = "productId";
    public static final String SHIP_QUANTITY = "shipQuantity";
    public static final String RETURN_QUANTITY = "returnQuantity";
    public static final String CART_ODER_ID = "orderId";

    //specification table
    public static final String SPECIFICATION_TABLE = "Specification";
    public static final String SCREEN = "screen";
    public static final String FRONT_CAMERA = "frontCamera";
    public static final String BACK_CAMERA = "backCamera";
    public static final String OS = "os";
    public static final String CHIPSET = "chipset";
    public static final String CPU = "cpu";
    public static final String SDCARD = "sdcard";
    public static final String SIMNUMBER = "simNumber";
    public static final String BATTERY = "batery";
    public static final String CONECTION = "conection";
    public static final String INTERNAL_STORAGE = "internalStorage";
    public static final String RAM = "ram";

    //banner table
    public static final String BANNER_TABLE = "Banner";
    public static final String BANNER_URL = "imageUrl";

    //Role
    public static final String ROLE_GUEST = "Guest";
}
