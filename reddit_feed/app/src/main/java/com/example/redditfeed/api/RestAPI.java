package com.example.redditfeed.api;

import com.example.redditfeed.models.RedditNewsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RestAPI {
    private final RedditApi redditApi;

    public RestAPI() {
        redditApi = new Retrofit.Builder()
                .baseUrl("https://reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RedditApi.class);
    }

    public Call<RedditNewsResponse> getNews(String after, Integer limit) {
        return redditApi.getTop(after, String.valueOf(limit));
    }
}
