package com.egleey.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.egleey.R;
import com.egleey.api.engine.Deserializer;
import com.egleey.api.models.Device;
import com.egleey.api.models.DeviceResponseObject;
import com.egleey.api.socketing.EgleeySocket;
import com.egleey.main.presenter.MainModel;
import com.egleey.main.utils.MainConstants;
import com.egleey.settings.SettingsActivity;


/**
 * Created by AMD on 12/3/16.
 */

public class MainPresenter implements Presenter<MainModel>, EgleeySocket.ConnectionListener{
    private final String TAG = getClass().getSimpleName();

    private MainModel mainModel;
    private EgleeySocket egleeySocket;

    public MainPresenter(MainModel mainModel) {
        attachView(mainModel);
    }

    @Override
    public void attachView(MainModel view) {
        this.mainModel = view;
        egleeySocket = new EgleeySocket(view.getContext());
        egleeySocket.addConnectionListener(this);
    }

    @Override
    public void detachView() {
        this.mainModel = null;
        egleeySocket.removeConnectionListener();
    }

    public void startEgleeyServices(@Nullable String... event) {
        egleeySocket.connectSocket();
        if (event != null) {
            for (String e: event) {
                egleeySocket.addEvent(e);
            }
        }
    }

    public void stopEgleeyServices(@Nullable String... event) {
        mainModel.killEgleey();
        egleeySocket.disconnectSocket();
        if (event != null) {
            for (String e: event) {
                egleeySocket.removeEvent(e);
            }
        } else {
            egleeySocket.removeEvent(null);
        }
    }

    public void commandStream(String command, String key, long id) {
        egleeySocket.emit(command, key, id);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainConstants.CREDENTIAL_REQUIRED_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "onActivityResult: ");
            egleeySocket.connectSocket();
        }
    }

    @Override
    public void onConnectionStatusChanged(EgleeySocket.ConnectionStatus status) {
        Log.e(TAG, "onConnectionStatusChanged: " + status.name());
        Snackbar snackbar = mainModel
                .getSnackbar(mainModel.getContext().getString(R.string.disconnected_cred_socket_message_content))
                .setAction(mainModel.getContext().getString(R.string.retry_button),
                        v -> egleeySocket.connectSocket());
        switch (status) {
            case WRONG_CREDENTIAL:
                snackbar.setAction(mainModel.getContext().getString(R.string.settings_button),
                        v -> {
                            Bundle extras = new Bundle();
                            extras.putBoolean(MainConstants.WRONG_SOCKET_CREDENTIALS, true);
                            mainModel.getNavigator().navigateForResult(MainConstants.CREDENTIAL_REQUIRED_REQUEST_CODE,
                                    SettingsActivity.class, extras);
                        })
                        .show();
                break;
            case DISCONNECTED:
                snackbar.show();
                break;
            case CONNECTED:
                snackbar.setDuration(1).show();
                break;
        }
    }

    @Override
    public void onDataRecivied(String event, Object data) {
        Deserializer<DeviceResponseObject> deserializer = new Deserializer<>(data.toString(), DeviceResponseObject.class);
        mainModel.enrollEgleey(event, deserializer.getModel().getDevices());

        for (Device d: deserializer.getModel().getDevices()) {

            Log.d(TAG, "startEgleeyServices: " + d.toString());
        }
    }
}
