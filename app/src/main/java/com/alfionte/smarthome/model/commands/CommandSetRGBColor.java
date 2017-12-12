package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CommandSetRGBColor extends Command {

    private static final int MAX_RGB_COLOR = 16777215;

    public CommandSetRGBColor(int id,
                              int rgbValue,
                              SpeedEffect speedEffect,
                              @IntRange(from = 30) int duration) {
        super(id, "set_rgb");

        List<String> params = new ArrayList<>();

        params.add(String.valueOf(normalizeRGBColor(rgbValue)));
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));

        setParams(params);
    }

    private int normalizeRGBColor(int rgbValue) {

        int normalizedRGB = rgbValue;

        normalizedRGB = Math.abs(normalizedRGB);
        if (normalizedRGB > MAX_RGB_COLOR) {
            normalizedRGB = MAX_RGB_COLOR;
            Log.w("CommandSetRGBColor", "Color: " + rgbValue +
                    " has been normalized into " + normalizedRGB);
        }
        return normalizedRGB;
    }
}