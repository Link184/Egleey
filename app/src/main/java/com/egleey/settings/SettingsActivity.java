package com.egleey.settings;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.egleey.R;
import com.egleey.base.BaseActivity;
import com.egleey.main.utils.MainConstants;
import com.egleey.settings.fragment.SettingsFragment;

/**
 * Created by AMD on 12/3/16.
 */

public class SettingsActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
    }

    private void initViews() {
        if (getIntent().getBooleanExtra(MainConstants.WRONG_SOCKET_CREDENTIALS, false)) {
            getMessageDialog(getString(R.string.wrong_cred_socket_message_content))
                    .title(getString(R.string.wrong_cred_socket_message_title))
                    .positiveText(getString(R.string.continue_button))
                    .show();
        } else {
            FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.settings_container, new SettingsFragment(), null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }
}
