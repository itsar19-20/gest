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
    private static Gson gson = new GsonBuilder()

            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(jsonElement.getAsString());
                }
            })

            // .setDateFormat("dd/MM/yyyy")
            .setLenient()
            .create();

    // Crea l'istanza Retrofit

    private static RetrofitUtils retrofitUtilsInstance = null;
    // Non ha molto senso creare una costante da chiamare una sola volta
    // private static final String BASE_URL = "http://localhost:8080/";
    private ApiEndPoint apiEndPoint;

    public static RetrofitUtils getInstance() {
        try {
            if (retrofitUtilsInstance == null) retrofitUtilsInstance = new RetrofitUtils();
            return retrofitUtilsInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private RetrofitUtils() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.122:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            this.apiEndPoint = retrofit.create(ApiEndPoint.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApiEndPoint getApiEndPoint() { return this.apiEndPoint; }

}
