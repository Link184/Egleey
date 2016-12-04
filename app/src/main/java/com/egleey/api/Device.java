package com.egleey.api;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by AMD on 12/3/16.
 */

public class Device {
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private LinkedTreeMap<String, String> data;
    @SerializedName("devices")
    private List<Device> devices;

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", data=" + data +
                ", devices=" + devices +
                '}';
    }
}
