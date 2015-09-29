package com.project.mobileonline.models;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class News {
    String imageLink;
    String title;
    String summary;

    public News() {
    }

    public News(String imageLink, String title, String summary) {
        this.imageLink = imageLink;
        this.title = title;
        this.summary = summary;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}
