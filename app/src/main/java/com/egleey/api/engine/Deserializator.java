package com.egleey.api.engine;

import com.google.gson.Gson;

/**
 * Created by AMD on 12/3/16.
 */

public class Deserializator<M> {
    private M models;

    public Deserializator(String reader, Class<M> model) {
        Gson gson = new Gson();
        models = gson.fromJson(reader, model);
    }

    public M getModel() {
        return models;
    }
}
