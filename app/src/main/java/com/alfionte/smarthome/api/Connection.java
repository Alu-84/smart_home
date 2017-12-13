package com.alfionte.smarthome.api;

import android.util.Log;

import com.alfionte.smarthome.model.commands.Command;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Connection {

    public Observable<String> sendCommand(final String host, final int port, final Command commandModel) {

        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                Socket socket = new Socket(host, port);

                final OutputStream outputStream = socket.getOutputStream();

                final BufferedOutputStream stream = new BufferedOutputStream(outputStream);
                stream.write((commandModel.toMessageString()).getBytes());
                stream.flush();


                final InputStream inputStream = socket.getInputStream();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                final String socketReceive = bufferedReader.readLine();

                Log.d("TCP receive","socketReceive "+ socketReceive);

                emitter.onNext(socketReceive);
                emitter.onComplete();
            }
        });
    }

}
