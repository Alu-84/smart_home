package com.alfionte.smarthome.model.commands;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class CommandSetPower extends Command {

    public CommandSetPower(int id,
                           boolean isOn,
                           SpeedEffect speedEffect,
                           @IntRange(from = 30) int duration,
                           PowerMode powerMode) {
        super(id, "set_power");

        List<String> params = new ArrayList<>();
        params.add(isOn ? "on" : "off");
        params.add(speedEffect.toString());
        params.add(String.valueOf(duration));
        params.add(String.valueOf(powerMode.getValue()));

        setParams(params);
    }

    public enum PowerMode {

        NORMAL(0),  // default
        COLOR_TEMPERATURE(1),
        COLOR_RGB(2),
        COLOR_HSV(3),
        COLOR_FLOW(4),
        NIGHT_LIGHT(5);

        private int mValue;

        PowerMode(int value) {
            mValue = value;
        }

        public int getValue() {
            return mValue;
        }
    }
}