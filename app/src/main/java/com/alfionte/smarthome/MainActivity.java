package com.alfionte.smarthome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfionte.smarthome.api.Connection;
import com.alfionte.smarthome.api.Scan;
import com.alfionte.smarthome.model.NotifyModel;
import com.alfionte.smarthome.model.commands.Command;
import com.alfionte.smarthome.model.commands.CommandToggle;
import com.alfionte.smarthome.parser.NotifyParser;

import java.net.InetSocketAddress;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.alfionte.smarthome.model.NotifyModel.KEY_ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button connect = findViewById(R.id.yee_connection);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Scan()
                        .scan()
                        .observeOn(Schedulers.io())
                        .map(new NotifyParser())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .map(new Function<NotifyModel, Object>() {
                            @Override
                            public Object apply(NotifyModel notifyModel) throws Exception {

                                final String id = notifyModel.getNotifyMap().get(KEY_ID).substring(2);
                                final Integer yeeId = Integer.parseInt(id, 16);

                                final Command toggle = new CommandToggle(1);
                                final Connection connection = new Connection();
                                final InetSocketAddress address = notifyModel.getAddress();
                                return connection.sendCommand(address.getHostName(), address.getPort(), toggle).subscribe();
                            }
                        })
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Log.d("SMART_HOME", "Boom!");
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("SMART_HOME", throwable.toString());
                            }
                        });
            }
        });
    }
}
