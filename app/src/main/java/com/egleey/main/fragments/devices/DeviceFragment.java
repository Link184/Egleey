package com.egleey.main.fragments.devices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egleey.R;
import com.egleey.base.BaseFragment;
import com.egleey.main.fragments.devices.adapter.DevicesAdapter;
import com.egleey.main.fragments.devices.adapter.model.Device;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AMD on 11/13/16.
 */

public class DeviceFragment extends BaseFragment {
    @BindView(R.id.deviceRecyclerView)
    RecyclerView devicesRecycler;

    private DevicesAdapter devicesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
//        devicesAdapter = new DevicesAdapter(initDummyDevices());
//        devicesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        devicesRecycler.setAdapter(devicesAdapter);
    }

    private List<Device> initDummyDevices() {
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            devices.add(new Device("device " + i));
        }
        return devices;
    }
}
