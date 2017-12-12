package com.alfionte.smarthome.model.commands;

import org.junit.Assert;
import org.junit.Test;


public class CommandSetBrightTest {

    @Test
    public void testBrightSmooth() {

        final CommandSetBright commandSetBright = new CommandSetBright(1, 1, SpeedEffect.SMOOTH, 500);
        final String brightString = commandSetBright.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_bright\",\"params\":[\"1\",\"smooth\",\"500\"]}\r\n", brightString);
    }

    @Test
    public void testBrightSudden() {

        final CommandSetBright commandSetBright = new CommandSetBright(1, 100, SpeedEffect.SUDDEN, 200);
        final String setPowerJson = commandSetBright.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"set_bright\",\"params\":[\"100\",\"sudden\",\"200\"]}\r\n", setPowerJson);
    }
}