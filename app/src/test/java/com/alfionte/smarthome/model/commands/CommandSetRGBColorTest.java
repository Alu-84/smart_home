package com.alfionte.smarthome.model.commands;

import android.graphics.Color;

import org.junit.Assert;
import org.junit.Test;

public class CommandSetRGBColorTest {

    @Test
    public void testBrightSmooth() {

        final CommandSetRGBColor commandSetRgbColor = new CommandSetRGBColor(1, Color.BLUE, SpeedEffect.SMOOTH, 500);
        final String rgbColorJson = commandSetRgbColor.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_rgb\",\"params\":[\"16776961\",\"smooth\",\"500\"]}\r\n", rgbColorJson);
    }

    @Test
    public void testBrightSudden() {

        final CommandSetRGBColor commandSetBright = new CommandSetRGBColor(1, Color.GREEN, SpeedEffect.SUDDEN, 200);
        final String rgbColorJson = commandSetBright.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_rgb\",\"params\":[\"16711936\",\"sudden\",\"200\"]}\r\n", rgbColorJson);
    }
}