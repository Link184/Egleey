package com.egleey.api.socketing;

/**
 * Created by AMD on 12/11/16.
 */

public final class SocketEvents {
    public static final String DEVICE_NAMES = "device_name_list:update";

    public static final String STREAM_START = "stream_start";
    public static final String STREAM_STOP = "stream_stop";
    public static final String STREAM_FEEDBACK = "stream_feedback:" + SocketEvents.STREAM_DEVICE_DATA_KEY;
    public static final String STREAM_DEVICE_DATA_KEY = "device";
}
