package com.rizzoli.fatturoio.utils;

import com.rizzoli.fatturoio.model.Fattura;
import com.rizzoli.fatturoio.model.TTTesttt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    // TEST
    // //////////// //////////// //////////// //////////// //////////// ////////////

    @GET("test")
    Call<TTTesttt> doTestMethodGet();

    @GET("/test?x={paperino}")
    Call<TTTesttt> doTestMethodGetPath(@Path("paperino") String str);

    @GET("test")
    Call<TTTesttt> doTestMethodGetQuery(@Query("x") String str);

    @FormUrlEncoded
    @POST("test")
    Call<TTTesttt> doTestMethodPost(@Field("alfa") String str);

    @FormUrlEncoded
    @POST("test")
    Call<TTTesttt> doTestMethodPostMaxi(@Field("alfa") String alfa, @Field("bravo") String bravo, @Field("charlie") int charlie);

    @POST("test")
    Call<TTTesttt> doTestMethodPostBody(@Body TTTesttt test);

    @Multipart
    @POST("test")
    Call<TTTesttt> doTestMethodPostMultipart(@Part("alfa") String alfa, @Part("bravo") String bravo);

    // //////////// //////////// //////////// //////////// //////////// ////////////


    @POST("archivio/getAllMineInvoices")
    Call<List<Fattura>> getAllMineInvoices(@Field("user") Integer _id);
    // Call<List<Fattura>> getAllMineInvoices(@Body("user") Integer _id);

    @GET("archivio/getThisInvoice/{id}")
    Call<Fattura> getThisInvoice(@Path("id") Integer _id);

}
