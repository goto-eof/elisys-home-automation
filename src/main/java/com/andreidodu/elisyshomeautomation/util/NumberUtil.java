package com.andreidodu.elisyshomeautomation.util;

public class NumberUtil {
    public static  Double normalize(Double number) {
        return Math.floor(number * 100) / 100;
    }
}
