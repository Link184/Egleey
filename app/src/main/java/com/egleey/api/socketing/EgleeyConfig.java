package com.egleey.api.socketing;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AMD on 12/3/16.
 */

class EgleeyConfig {
    private final String HOST_KEY = "host";
    private final String PORT_KEY = "port";
    private final String EMAIL_KEY = "email";
    private final String INTERVAL_KEY = "interval";

    private String host;
    private String email;
    private String type;
    private long interval;

    /**
     * A constructor that holds all configurables for {@link EgleeySocket}</>
     */
    EgleeyConfig(SharedPreferences preferences) {
        this.host = "http://"
                .concat(preferences.getString(HOST_KEY, "")
                .concat(":")
                .concat(preferences.getString(PORT_KEY, "")));
        this.email = preferences.getString(EMAIL_KEY, "");
        this.type = "user";
        this.interval = Long.parseLong(preferences.getString(INTERVAL_KEY, "5000"));
    }

    String getSocketOpts() {
        return "email=" + email + "&type=" + type;
    }

    String getHost() {
        return host;
    }

    String getEmail() {
        return email;
    }

    String getType() {
        return type;
    }

    public long getInterval() {
        return interval;
    }

    /**
     * @return JSON object which contains all configuration for stream manipulation
     */
    public JSONObject getStramConfig () {
        JSONObject streamConfig = new JSONObject();
        try {
            streamConfig.put(INTERVAL_KEY, interval);
            streamConfig.put("key", "test");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return streamConfig;
    }
}
