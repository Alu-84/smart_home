package com.alfionte.smarthome.model.commands;


import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

abstract public class Command {

    private int id;
    private String method;

    private List<String> params;

    protected Command(int id, String command, List<String> params) {
        this.id = id;
        this.method = command;
        this.params = params;
    }

    protected Command(int id, String command) {
        this.id = id;
        this.method = command;
        this.params = Collections.emptyList();
    }

    public String toMessageString() {
        return new Gson().toJson(this) + "\r\n";
    }

    protected void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command that = (Command) o;

        if (id != that.id) return false;
        if (method != null ? !method.equals(that.method) : that.method != null)
            return false;
        return params != null ? params.equals(that.params) : that.params == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }
}
