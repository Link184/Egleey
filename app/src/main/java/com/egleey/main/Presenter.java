package com.egleey.main;

import com.egleey.api.socketing.EgleeySocket;
/**
 * Created by AMD on 12/3/16.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();

}
