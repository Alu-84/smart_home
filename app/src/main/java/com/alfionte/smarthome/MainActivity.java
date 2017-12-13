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
import com.alfionte.smarthome.model.commands.CommandSetRGBColor;
import com.alfionte.smarthome.model.commands.SpeedEffect;
import com.alfionte.smarthome.parser.NotifyParser;
import com.alfionte.smarthome.utils.ColorUtils;

import java.net.InetSocketAddress;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

                                final int rgbColor = ColorUtils.toYeeRGBColor("green");
                                final CommandSetRGBColor command = new CommandSetRGBColor(1, 444, SpeedEffect.SMOOTH, 300);
                                final Connection connection = new Connection();
                                final InetSocketAddress address = notifyModel.getAddress();
                                return connection.sendCommand(address.getHostName(), address.getPort(), command).subscribe();
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
