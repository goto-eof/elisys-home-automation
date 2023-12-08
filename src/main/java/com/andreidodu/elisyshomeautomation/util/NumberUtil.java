package com.andreidodu.elisyshomeautomation.util;

public class NumberUtil {
    public static Double normalize(Double number) {
        if (number == null) {
            return 0.0;
        }
        return Math.floor(number * 100) / 100;
    }
}
