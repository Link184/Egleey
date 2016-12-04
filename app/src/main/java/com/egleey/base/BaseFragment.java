package com.egleey.base;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by AMD on 11/13/16.
 */

public class BaseFragment extends Fragment {

    protected void addFragment(int containerViewId, android.support.v4.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, null);
        fragmentTransaction.commit();
    }
}
