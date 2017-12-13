package com.alfionte.smarthome.utils;

import android.graphics.Color;

public class ColorUtils {

    private static final int MAX_COLOR = 16777216;

    public static int toYeeRGBColor(String color) {
        return Color.parseColor(color) + MAX_COLOR;
    }
}
