package com.alfionte.smarthome.model.commands;

import org.junit.Assert;
import org.junit.Test;

public class CommandSetPowerTest {

    @Test
    public void testPowerOnSmooth() {

        final CommandSetPower commandSetPower = new CommandSetPower(1, true, SpeedEffect.SMOOTH, 500);
        final String setPowerJson = commandSetPower.toMessageString();
        Assert.assertEquals( "{\"id\":1,\"method\":\"set_power\",\"params\":[\"on\",\"smooth\",\"500\"]}\r\n" , setPowerJson);
    }

    @Test
    public void testPowerOffSudden() {

        final CommandSetPower commandSetPower = new CommandSetPower(1, false, SpeedEffect.SUDDEN, 200);
        final String setPowerJson = commandSetPower.toMessageString();
        Assert.assertEquals( "{\"id\":1,\"method\":\"set_power\",\"params\":[\"off\",\"sudden\",\"200\"]}\r\n" , setPowerJson);
    }
}