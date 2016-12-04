package com.egleey.main.fragments.devices.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egleey.R;
import com.egleey.api.Device;

import java.util.List;


/**
 * Created by AMD on 11/13/16.
 */

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {
    private Device[] devices;

    public DevicesAdapter(Device[] devices) {
        this.devices = devices;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        holder.deviceName.setText(devices[position].getName());
    }

    @Override
    public int getItemCount() {
        return devices.length;
    }


    protected class DeviceViewHolder extends RecyclerView.ViewHolder{
        private TextView deviceName;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            deviceName = (TextView) itemView.findViewById(R.id.deviceName);

            itemView.setOnClickListener(view -> {
                List<Device> deviceList = DevicesAdapter.this.devices[getAdapterPosition()].getDevices();
                if (deviceList != null) {
                    Device[] subDevices = new Device[deviceList.size()];
                    for (int i = 0; i < deviceList.size(); i++) {
                        subDevices[i] = deviceList.get(i);
                    }
                }
            });
        }
    }
}
