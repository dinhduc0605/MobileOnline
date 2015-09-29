package com.project.mobileonline.models;

/**
 * Created by Nguyen Dinh Duc on 9/21/2015.
 */
public class ProductGridItem {
    String urlImage;
    String productName;
    String productPrice;

    public ProductGridItem(){

    }
    public ProductGridItem(String productName, String urlImage, String productPrice) {
        this.productName = productName;
        this.urlImage = urlImage;
        this.productPrice = productPrice;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
