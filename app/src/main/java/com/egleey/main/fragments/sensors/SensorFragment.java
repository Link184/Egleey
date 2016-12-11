package com.egleey.main.fragments.sensors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egleey.R;
import com.egleey.api.models.Device;
import com.egleey.base.BaseFragment;
import com.egleey.main.fragments.sensors.adapter.SensorAdapter;
import com.egleey.main.utils.MainConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by AMD on 11/13/16.
 */

public class SensorFragment extends BaseFragment{
    RecyclerView sensorRecycler;

    private SensorAdapter sensorAdapter;

    public static SensorFragment newInstance(List<Device> devices) {
        SensorFragment deviceFragment = new SensorFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MainConstants.KEY_DEVICES_ARGUMENTS, new ArrayList<>(devices));
        deviceFragment.setArguments(bundle);
        return deviceFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View v) {
        List<Device> devices = getArguments().getParcelableArrayList(MainConstants.KEY_DEVICES_ARGUMENTS);
        sensorRecycler = (RecyclerView) v.findViewById(R.id.sensorRecyclerView);
        sensorAdapter = new SensorAdapter(devices);
        sensorRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        sensorRecycler.setAdapter(sensorAdapter);
    }
}
