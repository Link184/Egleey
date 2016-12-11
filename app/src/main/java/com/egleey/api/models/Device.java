package com.egleey.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by AMD on 12/3/16.
 */

public class Device implements Parcelable{
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private LinkedTreeMap<String, String> data;
    @SerializedName("devices")
    private List<Device> devices;

    protected Device(Parcel in) {
        id = in.readLong();
        name = in.readString();
        devices = in.createTypedArrayList(Device.CREATOR);
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedTreeMap<String, String> getData() {
        return data;
    }

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeTypedList(devices);
    }
}
