package com.example.reneewu.news;

import com.example.reneewu.news.model.NYTimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by reneewu on 2/20/2017.
 */

public interface nytimesService {
    @GET("articlesearch.json")
    Call<NYTimes> articlesearch(@Query("api-key") String apiKey,
                                @Query("q") String query,
                                @Query("page") int page,
                                @Query("sort") String sort,
                                @Query("begin_date") String begin_date,
                                @Query("fq") String fq) ;
    @GET("articlesearch.json")
    Call<NYTimes> articlesearch(@Query("api-key") String apiKey,
                                @Query("q") String query,
                                @Query("page") int page,
                                @Query("sort") String sort,
                                @Query("begin_date") String begin_date ) ;
    @GET("articlesearch.json")
    Call<NYTimes> articlesearch(@Query("api-key") String apiKey,
                                @Query("q") String query,
                                @Query("page") int page,
                                @Query("sort") String sort) ;

    @GET("articlesearch.json")
    Call<NYTimes> articlesearch(@Query("api-key") String apiKey,
                                @Query("q") String query,
                                @Query("page") int page);



}
