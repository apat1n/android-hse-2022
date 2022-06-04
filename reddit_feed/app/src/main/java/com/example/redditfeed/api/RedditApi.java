package com.example.redditfeed.api;

import com.example.redditfeed.models.RedditNewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("top.json")
    public Call<RedditNewsResponse> getTop(@Query("after") String after, @Query("limit") String limit);
}
