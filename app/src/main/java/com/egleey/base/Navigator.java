package com.egleey.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.egleey.main.MainActivity;
import com.egleey.settings.SettingsActivity;

/**
 * Created by AMD on 12/4/16.
 */

public class Navigator {
    private BaseActivity context;
    private Intent intent;

    public Navigator(BaseActivity context) {
        this.context = context;
    }

    private void navigate(boolean closeCurrent, Class<? extends AppCompatActivity> destination,
                          Bundle extras, int... flags) {
        intent = new Intent(context,  destination);
        if (flags != null) {
            for (int i: flags) {
                intent.addFlags(i);
            }
        }

        if (extras != null) {
            intent.putExtras(extras);
        }

        context.startActivity(intent);
        if (closeCurrent) {
            context.finish();
        }
    }

    public void navigateForResult(int requestCode, Class<? extends AppCompatActivity> destination,
                                  Bundle extras, int... flags) {
        intent = new Intent(context,  destination);
        if (flags != null) {
            for (int i: flags) {
                intent.addFlags(i);
            }
        }

        if (extras != null) {
            intent.putExtras(extras);
        }

        context.startActivityForResult(intent, requestCode);
    }

    public void navigateToSettings(boolean closeCurrent, int... flags) {
        navigateToSettings(closeCurrent, null, flags);
    }

    public void navigateToSettings(boolean closeCurrent, @Nullable Bundle extras, @Nullable int... flags) {
        navigate(closeCurrent, SettingsActivity.class, extras, flags);
    }

    public void navigateToMain(boolean closeCurrent, @Nullable int... flags) {
        navigateToMain(closeCurrent, null, flags);
    }

    public void navigateToMain(boolean closeCurrent, @Nullable Bundle extras, @Nullable int... flags) {
        navigate(closeCurrent, MainActivity.class, extras, flags);
    }
}
