package com.alfionte.smarthome.parser;

import android.text.TextUtils;
import android.util.Log;

import com.alfionte.smarthome.model.NotifyModel;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Function;

public class NotifyParser implements Function<DatagramPacket, NotifyModel> {

    @Override
    public NotifyModel apply(DatagramPacket datagramPacket) throws Exception {

        final TextUtils.SimpleStringSplitter lineSplitter = new TextUtils.SimpleStringSplitter('\n');
        final Map<String, String> responseMap = new HashMap<>();

        lineSplitter.setString(new String(datagramPacket.getData()));

        while (lineSplitter.hasNext()) {

            final String key;
            final String value;
            final String line = lineSplitter.next();

            try {

                final String[] split = line.split(":", 2);
                key = split[0].toUpperCase();
                value = split[1].trim();

                responseMap.put(key, value);

            } catch (IndexOutOfBoundsException e) {
                // Log.d("NotifyParser", "Not splittable line: " + line);
            }
        }

        return new NotifyModel(responseMap);
    }
}
