package com.rizzoli.fatturoio.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class VolleyUtils {

    private final static String BASE_URL = "http://192.168.1.122:8080/";
    private static RequestQueue queue = null;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy MM dd")
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(jsonElement.getAsString());
                }
            })
            .setLenient()
            .create();

    public static String url(String servlet) {
        return BASE_URL + servlet;
    }

    public static RequestQueue getRequestQueueInstance(Context context) {
        if (queue == null) queue = Volley.newRequestQueue(context);
        return queue;
    }

    public static Gson getGsonInstance() {
        return gson;
    }

}
