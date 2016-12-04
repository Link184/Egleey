package com.egleey.main.fragments.sensors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egleey.R;
import com.egleey.base.BaseFragment;
import com.egleey.main.fragments.sensors.adapter.SensorAdapter;
import com.egleey.main.fragments.sensors.adapter.model.Sensor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AMD on 11/13/16.
 */

public class SensorFragment extends BaseFragment{
    @BindView(R.id.sensorRecyclerView)
    RecyclerView sensorRecycler;

    private SensorAdapter sensorAdapter;


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
        initViews();
    }

    private void initViews() {
        Bundle arguments = getArguments();
        sensorAdapter = new SensorAdapter(initDummySensors());
    }

    private List<Sensor> initDummySensors() {
        List<Sensor> sensors = new ArrayList<>();

        return null;
    }
}
