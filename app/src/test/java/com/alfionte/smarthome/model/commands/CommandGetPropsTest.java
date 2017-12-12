package com.alfionte.smarthome.model.commands;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CommandGetPropsTest {

    @Test
    public void testGetProps() {

        final CommandGetProps commandGetProps = new CommandGetProps(1, Arrays.asList("bright", "power"));
        final String getPropsJson = commandGetProps.toMessageString();
        Assert.assertEquals("{\"id\":1,\"method\":\"get_prop\",\"params\":[\"bright\",\"power\"]}\r\n", getPropsJson);
    }
}