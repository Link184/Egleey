package com.egleey.api.socketing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by AMD on 12/3/16.
 */

class EgleeyConfig {
    private final String HOST_KEY = "host";
    private final String PORT_KEY = "port";
    private final String EMAIL_KEY = "email";

    private String host;
    private String email;
    private String type;

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
}
