package com.egleey.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMD on 12/11/16.
 */

public class DeviceResponseObject implements Parcelable{
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Device> devices;

    protected DeviceResponseObject(Parcel in) {
        status = in.readString();
        devices = in.createTypedArrayList(Device.CREATOR);
    }

    public static final Creator<DeviceResponseObject> CREATOR = new Creator<DeviceResponseObject>() {
        @Override
        public DeviceResponseObject createFromParcel(Parcel in) {
            return new DeviceResponseObject(in);
        }

        @Override
        public DeviceResponseObject[] newArray(int size) {
            return new DeviceResponseObject[size];
        }
    };

    public String getStatus() {
        return status;
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
        parcel.writeString(status);
        parcel.writeTypedList(devices);
    }
}
