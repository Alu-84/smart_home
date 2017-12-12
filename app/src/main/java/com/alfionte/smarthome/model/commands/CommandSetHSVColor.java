package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class CommandSetHSVColor extends Command {

    public CommandSetHSVColor(int id,
                              @IntRange(from = 0, to = 359) int hue,
                              @IntRange(from = 0, to = 100) int sat,
                              SpeedEffect speedEffect,
                              @IntRange(from = 30) int duration) {
        super(id, "set_hsv");

        List<String> params = new ArrayList<>();

        params.add(String.valueOf(hue));
        params.add(String.valueOf(sat));
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));

        setParams(params);
    }
}