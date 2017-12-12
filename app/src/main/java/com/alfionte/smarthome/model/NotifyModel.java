package com.alfionte.smarthome.model;


import java.net.InetSocketAddress;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotifyModel {

    private static final Pattern LOCATION_PATTERN = Pattern.compile("yeelight://(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d+)");

    // list of known keys
    public static final String KEY_ID = "ID";
    public static final String KEY_LOCATION = "LOCATION";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_MODEL = "MODEL";
    public static final String KEY_CACHE_CONTROL = "CACHE-CONTROL";
    public static final String KEY_EXT = "EXT";
    public static final String KEY_VERSION = "FW_VER";
    public static final String KEY_SERVER = "SERVER";
    public static final String KEY_SUPPORTED_METHODS = "SUPPORT";
    public static final String KEY_POWER = "POWER";
    public static final String KEY_BRIGHT = "BRIGHT";
    public static final String KEY_COLOR_MODE = "COLOR_MODE";
    public static final String KEY_COLOR_MODE_CT = "CT";
    public static final String KEY_COLOR_MODE_RGB = "RGB";
    public static final String KEY_COLOR_MODE_HUE = "HUE";
    public static final String KEY_COLOR_MODE_SAT = "SAT";

    private Map<String, String> notifyMap;

    public NotifyModel(Map<String, String> notifyMap) {
        this.notifyMap = notifyMap;
    }

    public InetSocketAddress getAddress() {

        String host = null;
        int port = 0;

        final String location = notifyMap.get(KEY_LOCATION);
        final Matcher matcher = LOCATION_PATTERN.matcher(location);

        if (matcher.find()) {
            host = matcher.group(1);
            port = Integer.valueOf(matcher.group(2));
        }
        return new InetSocketAddress(host, port);
    }

    public Map<String, String> getNotifyMap() {
        return notifyMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifyModel that = (NotifyModel) o;

        return notifyMap != null ? notifyMap.equals(that.notifyMap) : that.notifyMap == null;
    }

    @Override
    public int hashCode() {
        return notifyMap != null ? notifyMap.hashCode() : 0;
    }
}
