package com.egleey.api.engine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by AMD on 12/3/16.
 */

public class Deserializator<R extends String, M> {
    private List<M> models;

    public Deserializator(R reader) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<M>>(){}.getType();
        models = gson.fromJson(reader, listType);
    }

    public List<M> getModels() {
        return models;
    }
}
