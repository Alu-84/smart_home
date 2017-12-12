package com.alfionte.smarthome.model.commands;

public enum SpeedEffect {

    SUDDEN, SMOOTH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}