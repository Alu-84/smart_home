package com.alfionte.smarthome.model.commands;

import java.util.List;

public class CommandGetProps extends Command {

    public CommandGetProps(int id, List<String> params) {
        super(id, "get_prop", params);
    }
}