package com.egleey.main.fragments.devices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egleey.R;
import com.egleey.R2;
import com.egleey.api.Device;
import com.egleey.base.BaseFragment;
import com.egleey.main.fragments.devices.adapter.DevicesAdapter;
import com.egleey.main.utils.MainConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AMD on 11/13/16.
 */

public class DeviceFragment extends BaseFragment {
    @BindView(R2.id.deviceRecyclerView)
    RecyclerView devicesRecycler;

    private DevicesAdapter devicesAdapter;

    public static DeviceFragment newInstance(Device[] devices) {
        DeviceFragment deviceFragment = new DeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(MainConstants.KEY_DEVICES_ARGUMENTS, devices);
        deviceFragment.setArguments(bundle);
        return deviceFragment;
    }

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
        Device[] devices = (Device[]) getArguments().getParcelableArray(MainConstants.KEY_DEVICES_ARGUMENTS);
        devicesAdapter = new DevicesAdapter(devices);
        devicesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        devicesRecycler.setAdapter(devicesAdapter);
    }
}
