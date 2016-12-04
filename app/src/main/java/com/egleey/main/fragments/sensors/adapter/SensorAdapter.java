package com.egleey.main.fragments.sensors.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egleey.R;
import com.egleey.main.fragments.sensors.adapter.model.Sensor;

import java.util.List;

/**
 * Created by AMD on 11/13/16.
 */

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {
    private List<Sensor> sensors;

    public SensorAdapter(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sensor, parent, false);
        return new SensorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SensorViewHolder holder, int position) {
        holder.sensorName.setText(sensors.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    protected class SensorViewHolder extends RecyclerView.ViewHolder{
        protected TextView sensorName;

        public SensorViewHolder(View itemView) {
            super(itemView);
            sensorName = (TextView) itemView.findViewById(R.id.sensorName);
        }
    }
}
