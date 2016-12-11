package com.egleey.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.egleey.R;
import com.egleey.api.models.Device;
import com.egleey.api.socketing.SocketEvents;
import com.egleey.base.BaseActivity;
import com.egleey.base.BaseFragment;
import com.egleey.base.Navigator;
import com.egleey.main.fragments.devices.DeviceFragment;
import com.egleey.main.fragments.sensors.SensorFragment;
import com.egleey.main.presenter.MainModel;

import java.util.List;

public class MainActivity extends BaseActivity implements MainModel{
    private final String TAG = getClass().getSimpleName();
    private MainPresenter mainPresenter;

    private BaseFragment[] fragments = {new DeviceFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

        initViews();
    }

    private void initViews() {
//        for (BaseFragment fragment : fragments) {
//            addFragment(R.id.mainContent, fragment);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            getNavigator().navigateToSettings(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return super.getNavigator();
    }

    @Override
    public Snackbar getSnackbar(String message) {
        return super.getSnackbar(findViewById(R.id.mainContent), message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.stopEgleeyServices(SocketEvents.DEVICE_NAMES);
        mainPresenter.commandStream(SocketEvents.STREAM_STOP, SocketEvents.STREAM_DEVICE_DATA_KEY, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.startEgleeyServices(SocketEvents.DEVICE_NAMES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void enrollEgleey(String event, List<Device> devices) {
        switch (event) {
            case SocketEvents.DEVICE_NAMES:
                addFragment(R.id.mainContent, DeviceFragment.newInstance(devices));
                break;
            case SocketEvents.STREAM_FEEDBACK:
                addFragment(R.id.mainContent, SensorFragment.newInstance(devices));
                break;
        }
    }

    @Override
    public void killEgleey() {

    }

    @Override
    public void onDeviceSelected(long id) {
        Log.e(TAG, "onDeviceSelected: " + id);
        mainPresenter.commandStream(SocketEvents.STREAM_START, SocketEvents.STREAM_DEVICE_DATA_KEY, id);
        mainPresenter.startEgleeyServices(SocketEvents.STREAM_FEEDBACK);
    }
}
