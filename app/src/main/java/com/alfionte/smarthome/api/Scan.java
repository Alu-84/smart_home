package com.alfionte.smarthome.api;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Scan {

    private String SEARCH_UDP_HOST = "239.255.255.250";
    private int SEARCH_UDP_PORT = 1982;
    private byte[] SEARCH_UDP_MESSAGE_BYTES = "M-SEARCH * HTTP/1.1\r\nHOST:239.255.255.250:1982\r\nMAN:\"ssdp:discover\"\r\nST:wifi_bulb\r\n".getBytes();

    private DatagramPacket datagramPacket;

    public Observable<DatagramPacket> scan() {

        return Observable.create(new ObservableOnSubscribe<DatagramPacket>() {
            @Override
            public void subscribe(ObservableEmitter<DatagramPacket> observableEmitter) throws Exception {

                final DatagramSocket datagramSocket = new DatagramSocket();

                datagramPacket = new DatagramPacket(
                        SEARCH_UDP_MESSAGE_BYTES,
                        SEARCH_UDP_MESSAGE_BYTES.length,
                        InetAddress.getByName(SEARCH_UDP_HOST), SEARCH_UDP_PORT);

                datagramSocket.send(datagramPacket);

                final byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(receivePacket);
                Log.d("Scan", "receivePacket: " + new String(receivePacket.getData()));

                observableEmitter.onNext(receivePacket);

                datagramSocket.disconnect();
                observableEmitter.onComplete();
            }
        });
    }

}
