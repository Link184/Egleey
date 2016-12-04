package com.egleey.api.socketing;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by AMD on 11/13/16.
 */

public class EgleeySocket {
    private final String TAG = getClass().getSimpleName();
    private Socket socket;
    private IO.Options opts = new IO.Options();

    private ConnectionListener connectionListener;

    public EgleeySocket(Context context) {
        EgleeyConfig config = new EgleeyConfig(PreferenceManager.getDefaultSharedPreferences(context));

        String host = config.getHost();
        opts.query = config.getSocketOpts();
        try {
            socket = IO.socket(host, opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, args -> connectionListener.onConnectionStatusChanged(ConnectionStatus.CONNECTED));
        socket.on(Socket.EVENT_DISCONNECT, args -> connectionListener.onConnectionStatusChanged(ConnectionStatus.DISCONNECTED));
    }

    public void connectSocket() {
        try {
            socket.connect();
        } catch (NullPointerException e) {
            connectionListener.onConnectionStatusChanged(ConnectionStatus.WRONG_CREDENTIAL);
        }
    }

    public void disconnectSocket() {
        socket.disconnect();
    }

    public void addEvent(@NonNull String event) {
        socket.on(event, args -> {
            for (Object o: args) {
                connectionListener.onDataRecivied(event, o);
            }
        });
    }

    public void removeEvent(@Nullable String event) {
        if (event != null) {
            socket.off(event);
        } else {
            socket.off();
        }
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    public void removeConnectionListener() {
        this.connectionListener = null;
    }

    public enum ConnectionStatus {CONNECTED, DISCONNECTED, WRONG_CREDENTIAL}

    public interface ConnectionListener {
        void onConnectionStatusChanged(ConnectionStatus status);
        void onDataRecivied(String event, Object data);
    }
}
