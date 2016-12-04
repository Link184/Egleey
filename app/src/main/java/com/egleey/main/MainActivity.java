package com.egleey.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.egleey.R;
import com.egleey.R2;
import com.egleey.base.BaseActivity;
import com.egleey.base.Navigator;
import com.egleey.main.fragments.devices.DeviceFragment;
import com.egleey.main.presenter.MainModel;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainModel{
    private MainPresenter mainPresenter;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.titleField)
    TextView titleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            getNavigator().navigateToSettings(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleToolbar.setText(getString(R.string.main_activity_title));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Navigator getNavigator() {
        return super.getNavigator();
    }

    @Override
    public Snackbar getSnackbar(String message) {
//        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
//                .findViewById(android.R.id.content)).getChildAt(0);
        return super.getSnackbar(findViewById(R.id.mainContent), message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.stopEgleeyServices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.startEgleeyServices("message");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void enrollEgleey() {
        addFragment(R.id.mainContent, new DeviceFragment());
    }

    @Override
    public void killEgleey() {

    }
}
