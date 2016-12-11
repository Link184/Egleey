package com.egleey.main.presenter;


import android.content.Context;
import android.support.design.widget.Snackbar;

import com.egleey.api.models.Device;
import com.egleey.base.Navigator;

import java.util.List;

/**
 * Created by AMD on 12/3/16.
 */

public interface MainModel {
    Context getContext();

    Navigator getNavigator();

    Snackbar getSnackbar(String message);

    /**
     * Method need to be called when received response from Egleey services and need to prepare UI
     * @param event the event name where the data are from.
     * @param devices list to render
     */
    void enrollEgleey(String event, List<Device> devices);

    /**
     * Method need to be called when the activity become inactive
     */
    void killEgleey();

    /**
     * Method callback when user select a device.
     * @param id device id
     */
    void onDeviceSelected(long id);
}
