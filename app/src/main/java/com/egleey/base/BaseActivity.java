package com.egleey.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.egleey.R;

import butterknife.ButterKnife;

/**
 * Created by AMD on 11/13/16.
 */

public class BaseActivity extends AppCompatActivity {
    private Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {

        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, null);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int containerViewId, Fragment fragment) {

        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, null);
        fragmentTransaction.commit();
    }

    protected MaterialDialog.Builder getMessageDialog(String content) {
        return new MaterialDialog.Builder(this)
                .content(content);
    }

    protected Snackbar getSnackbar(View view, String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
    }

    protected Navigator getNavigator() {
        return navigator;
    }
}
