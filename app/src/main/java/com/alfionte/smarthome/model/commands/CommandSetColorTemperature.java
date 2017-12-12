package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class CommandSetColorTemperature extends Command {

    public CommandSetColorTemperature(int id,
                                      @IntRange(from = 1700, to = 6500) int colorTemperature,
                                      SpeedEffect speedEffect,
                                      @IntRange(from = 30) int duration) {
        super(id, "set_ct_abx");

        List<String> params = new ArrayList<>();
        params.add(String.valueOf(colorTemperature));
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));

        setParams(params);
    }
}