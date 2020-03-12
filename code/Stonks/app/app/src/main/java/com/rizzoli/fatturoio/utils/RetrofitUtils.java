package com.rizzoli.fatturoio.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    // Imposta il formato personalizato per la data
    Gson gson = new GsonBuilder()
            /*
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(jsonElement.getAsString());
                }
            })
             */
            .setDateFormat("dd/MM/yyyy")
            .create();

    // Crea l'istanza Retrofit

    private static RetrofitUtils retrofitUtilsInstance = null;
    public static final String BASE_URL = "http://localhost/8080/";
    private ApiEndPoint apiEndPoint;

    public static RetrofitUtils getInstance() {
        if (retrofitUtilsInstance == null) retrofitUtilsInstance = new RetrofitUtils();
        return retrofitUtilsInstance;
    }

    private RetrofitUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ApiEndPoint getApiEndPoint() { return this.apiEndPoint; }

}
