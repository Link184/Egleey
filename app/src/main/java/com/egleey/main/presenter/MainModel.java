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

    void initToolbar();

    /**
     * Method need to be called when received response from Egleey services and need to prepare UI
     * @param devices list to render
     */
    void enrollEgleey(List<Device> devices);

    /**
     * Method need to be called when the activity become inactive
     */
    void killEgleey();
}
