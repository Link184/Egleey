package com.egleey.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.egleey.R;
import com.egleey.api.socketing.EgleeySocket;
import com.egleey.base.BaseActivity;
import com.egleey.main.presenter.MainModel;
import com.egleey.main.utils.MainConstants;
import com.egleey.settings.SettingsActivity;


/**
 * Created by AMD on 12/3/16.
 */

public class MainPresenter implements Presenter<MainModel>{
    private final String TAG = getClass().getSimpleName();

    private MainModel mainModel;
    private EgleeySocket egleeySocket;

    public MainPresenter(MainModel mainModel) {
        attachView(mainModel);
    }

    @Override
    public void attachView(MainModel view) {
        this.mainModel = view;
        mainModel.initToolbar();
        egleeySocket = new EgleeySocket(view.getContext());
    }

    @Override
    public void detachView() {
        this.mainModel = null;
    }

    public void startEgleeyServices(@Nullable String... event) {
        if (egleeySocket.getConnectionStatus() == EgleeySocket.ConnectionStatus.CONNECTED ||
                performSocketConnect()) {
            String response = null;
            if (event != null) {
                for (String e: event) {
                    response += egleeySocket.addEvent(e);
                }
            }

//        Deserializator<String, Device> deserializator = new Deserializator<>(response);
//        for (Device d: deserializator.getModels()) {
//
//            Log.e(TAG, "startEgleeyServices: " + d.toString());
//        }
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

    private boolean performSocketConnect() {
        EgleeySocket.ConnectionStatus status = egleeySocket.connectSocket();
        Log.e(TAG, "performSocketConnect: " + status.name());
        switch (status) {
            case WRONG_CREDENTIAL:
                Bundle extras = new Bundle();
                extras.putBoolean(MainConstants.WRONG_SOCKET_CREDENTIALS, true);
                mainModel.getNavigator().navigateForResult(MainConstants.CREDENTIAL_REQUIRED_REQUEST_CODE,
                        SettingsActivity.class, extras);
                return false;
            case DISCONNECTED:
                mainModel.getSnackbar(mainModel.getContext().getString(R.string.disconnected_cred_socket_message_content))
                        .setAction(mainModel.getContext().getString(R.string.retry_button),
                                v -> performSocketConnect())
                        .show();
                return false;
            case CONNECTED:
                mainModel.enrollEgleey();
                return true;
            default:
                return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainConstants.CREDENTIAL_REQUIRED_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "onActivityResult: ");
            performSocketConnect();
        }
    }

}
