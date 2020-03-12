package com.rizzoli.fatturoio.utils;

import com.rizzoli.fatturoio.model.Fattura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    // TEST
    // //////////// //////////// //////////// //////////// //////////// ////////////

    @GET("test")
    Call<String> doTestMethodGet();

    // @GET("/test/{x}")
    // Call<String> doTestMethodGetSpecial(@Path("x") String str);
    @GET("test")
    Call<String> doTestMethodGetSpecial(@Query("x") String str);

    @POST("test")
    Call<String> doTestMethodPost(@Field("str") String str);

    @POST("test")
    Call<String> doTestMethodPostMaxi(@Field("alfa") String alfa, @Field("bravo") String bravo, @Field("charlie") int charlie);

    // //////////// //////////// //////////// //////////// //////////// ////////////


    @POST("archivio/getAllMineInvoices")
    Call<List<Fattura>> getAllMineInvoices(@Field("user") Integer _id);
    // Call<List<Fattura>> getAllMineInvoices(@Body("user") Integer _id);

    @GET("archivio/getThisInvoice/{id}")
    Call<Fattura> getThisInvoice(@Path("id") Integer _id);

}
