package com.egleey.api.socketing;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by AMD on 11/13/16.
 */

public class EgleeySocket {
    private final String TAG = getClass().getSimpleName();
    private Socket socket;
    private IO.Options opts = new IO.Options();

    private ConnectionStatus connectionStatus;

    public EgleeySocket(Context context) {
        EgleeyConfig config = new EgleeyConfig(PreferenceManager.getDefaultSharedPreferences(context));
        connectionStatus = ConnectionStatus.DISCONNECTED;

        String host = config.getHost();
        opts.query = config.getSocketOpts();
        try {
            socket = IO.socket(host, opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, args -> connectionStatus = ConnectionStatus.CONNECTED);
        socket.on(Socket.EVENT_DISCONNECT, args -> connectionStatus = ConnectionStatus.DISCONNECTED);
    }

    public ConnectionStatus connectSocket() {
        try {
            socket.connect();
        } catch (NullPointerException e) {
            connectionStatus = ConnectionStatus.WRONG_CREDENTIAL;
        }
        return connectionStatus;
    }

    public void disconnectSocket() {
        socket.disconnect();
    }

    public String addEvent(@NonNull String event) {
        final String[] result = new String[1];
        socket.on(event, args -> {
            String response = null;
            for (Object o: args) {
                Log.d(TAG, event + ": " + o.toString());
                response += o.toString();
            }
            result[0] = response;

        });
        return result[0];
    }

    public void removeEvent(@Nullable String event) {
        if (event != null) {
            socket.off(event);
        } else {
            socket.off();
        }
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public enum ConnectionStatus {CONNECTED, DISCONNECTED, WRONG_CREDENTIAL}
}
