package com.egleey.main.fragments.sensors.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egleey.R;
import com.egleey.api.models.Device;

import java.util.List;

/**
 * Created by AMD on 11/13/16.
 */

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {
    private List<Device> devices;

    public SensorAdapter(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sensor, parent, false);
        return new SensorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SensorViewHolder holder, int position) {
        holder.sensorName.setText(devices.get(position).getData().toString());
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    protected class SensorViewHolder extends RecyclerView.ViewHolder{
        protected TextView sensorName;

        public SensorViewHolder(View itemView) {
            super(itemView);
            sensorName = (TextView) itemView.findViewById(R.id.sensorName);
            itemView.setOnClickListener(v -> {

            });
        }
    }
}
