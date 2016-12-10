package com.egleey.settings.fragment;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.egleey.R;

/**
 * Created by AMD on 12/3/16.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
    private final String TAG = getClass().getSimpleName();
    private final String HOST_KEY = "host";
    private final String PORT_KEY = "port";
    private final String EMAIL_KEY = "email";
    private final String INTERVAL_KEY = "interval";

    private EditTextPreference host;
    private EditTextPreference port;
    private EditTextPreference email;
    private ListPreference intervalList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_settings);

        initSettingViews();
    }

    private void initSettingViews() {
        host = (EditTextPreference) findPreference(HOST_KEY);
        port = (EditTextPreference) findPreference(PORT_KEY);
        email = (EditTextPreference) findPreference(EMAIL_KEY);
        intervalList = (ListPreference) findPreference(INTERVAL_KEY);

        host.setSummary(getPreferenceManager().getSharedPreferences().getString(HOST_KEY, ""));
        port.setSummary(getPreferenceManager().getSharedPreferences().getString(PORT_KEY, ""));
        email.setSummary(getPreferenceManager().getSharedPreferences().getString(EMAIL_KEY, ""));
        intervalList.setSummary(getPreferenceManager().getSharedPreferences().getString(INTERVAL_KEY, ""));

        host.setOnPreferenceChangeListener(this);
        port.setOnPreferenceChangeListener(this);
        email.setOnPreferenceChangeListener(this);
        intervalList.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch (preference.getKey()) {
            case HOST_KEY:
                host.setText(o.toString());
                host.setSummary(o.toString());
                break;
            case PORT_KEY:
                port.setText(o.toString());
                port.setSummary(o.toString());
                break;
            case EMAIL_KEY:
                email.setText(o.toString());
                email.setSummary(o.toString());
                break;
            case INTERVAL_KEY:
                intervalList.setValue(o.toString());
                intervalList.setSummary(o.toString());
                break;
        }
        return false;
    }
}
