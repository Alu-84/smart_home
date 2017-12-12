package com.alfionte.smarthome.model.commands;

import org.junit.Assert;
import org.junit.Test;


public class CommandSetColorTemperatureTest {

    @Test
    public void testBrightSmooth() {

        final CommandSetColorTemperature setColorTemperature = new CommandSetColorTemperature(1, 1700, SpeedEffect.SMOOTH, 500);
        final String colorTemperatureString = setColorTemperature.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_ct_abx\",\"params\":[\"1700\",\"smooth\",\"500\"]}\r\n", colorTemperatureString);
    }

    @Test
    public void testBrightSudden() {

        final CommandSetColorTemperature setColorTemperature = new CommandSetColorTemperature(1, 6500, SpeedEffect.SUDDEN, 200);
        final String colorTemperatureString = setColorTemperature.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_ct_abx\",\"params\":[\"6500\",\"sudden\",\"200\"]}\r\n", colorTemperatureString);
    }

}