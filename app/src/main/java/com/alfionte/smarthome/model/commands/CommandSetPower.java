package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class CommandSetPower extends Command {

    public CommandSetPower(int id,
                           boolean isOn,
                           SpeedEffect speedEffect,
                           @IntRange(from = 30) int duration) {
        super(id, "set_power");

        List<String> params = new ArrayList<>();
        params.add(isOn ? "on" : "off");
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));

        setParams(params);
    }
}