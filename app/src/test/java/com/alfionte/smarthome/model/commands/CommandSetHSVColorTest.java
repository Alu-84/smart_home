package com.alfionte.smarthome.model.commands;

import org.junit.Assert;
import org.junit.Test;

public class CommandSetHSVColorTest {

    @Test
    public void testHSVSmooth() {

        final CommandSetHSVColor commandSetHSVColor = new CommandSetHSVColor(1, 359, 100, SpeedEffect.SMOOTH, 500);
        final String hsvColorJson = commandSetHSVColor.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_hsv\",\"params\":[\"359\",\"100\",\"smooth\",\"500\"]}\r\n", hsvColorJson);
    }

    @Test
    public void testHSVSudden() {

        final CommandSetHSVColor commandSetHSVColor = new CommandSetHSVColor(1, 359, 100, SpeedEffect.SUDDEN, 500);
        final String hsvColorJson = commandSetHSVColor.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_hsv\",\"params\":[\"359\",\"100\",\"sudden\",\"500\"]}\r\n", hsvColorJson);
    }

}