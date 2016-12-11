package com.egleey.api.engine;

import com.google.gson.Gson;

/**
 * Created by AMD on 12/3/16.
 */

public class Deserializer<M> {
    private M models;

    public Deserializer(String reader, Class<M> model) {
        Gson gson = new Gson();
        models = gson.fromJson(reader, model);
    }

    public M getModel() {
        return models;
    }
}
