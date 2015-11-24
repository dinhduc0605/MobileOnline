package com.project.mobileonline.utils;

import java.text.DecimalFormat;

/**
 * Created by nguyendinhduc on 11/23/15.
 */
public class StringProcess {
    public static String formatPrice(int price) {
        String s = DecimalFormat.getCurrencyInstance().format(price);
        return s.substring(0, s.length() - 3);
    }
}
