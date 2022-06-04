package com.example.redditfeed;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.example.redditfeed.api.RestAPI;
import com.example.redditfeed.db.RedditNewsItem;
import com.example.redditfeed.models.RedditChildrenResponse;
import com.example.redditfeed.models.RedditNewsResponse;

import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends ViewModel {
    private final RestAPI api = new RestAPI();
    private final ObservableList<RedditNewsItem> news = new ObservableArrayList<>();
    private static final int pageSize = 100;
    private String after = "";

    private Context context;
    private boolean isInitialized = false;
    private RedditNewsAdapter redditNewsAdapter;

    public FeedViewModel() {
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public void setRedditNewsAdapter(RedditNewsAdapter redditNewsAdapter) {
        this.redditNewsAdapter = redditNewsAdapter;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ObservableList<RedditNewsItem> getNews() {
        return news;
    }

    public void loadNews() {
        Call<RedditNewsResponse> callResponse = api.getNews(after, pageSize);
        callResponse.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<RedditNewsResponse> call, @NonNull Response<RedditNewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    after = response.body().getData().getAfter();
                    redditNewsAdapter.addNews(response.body()
                            .getData()
                            .getChildren()
                            .stream()
                            .map(RedditChildrenResponse::getData)
                            .map(r -> new RedditNewsItem(r.getUrl(), r.getAuthor(), r.getTitle(), r.getNumComments(), r.getCreated(), r.getThumbnail()))
                            .collect(Collectors.toList()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RedditNewsResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "cannot load news", Toast.LENGTH_LONG).show();
            }
        });
    }
}
