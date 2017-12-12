package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class CommandSetBright extends Command {

    public CommandSetBright(int id,
                            @IntRange(from = 1, to = 100) int brightness,
                            SpeedEffect speedEffect,
                            @IntRange(from = 30) int duration) {
        super(id, "set_bright");

        List<String> params = new ArrayList<>();
        params.add(String.valueOf(brightness));
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));

        setParams(params);
    }
}