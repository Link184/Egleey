package com.egleey.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by AMD on 12/3/16.
 */

public class Device {
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private Map<String, String> data;
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
