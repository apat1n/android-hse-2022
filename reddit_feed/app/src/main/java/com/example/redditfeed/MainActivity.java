package com.example.redditfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private FeedViewModel viewModel;
    private RedditNewsAdapter redditNewsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView feedRecyclerView = findViewById(R.id.feed);

        viewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        layoutManager = new LinearLayoutManager(this);
        redditNewsAdapter = new RedditNewsAdapter(this, viewModel.getNews());

        viewModel.setRedditNewsAdapter(redditNewsAdapter);
        viewModel.setContext(this);

        feedRecyclerView.setLayoutManager(layoutManager);
        feedRecyclerView.setAdapter(redditNewsAdapter);

        feedRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                viewModel.loadNews();
            }
        });
        feedRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, feedRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                redditNewsAdapter.openURL(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        ImageButton upButton = findViewById(R.id.up_button);
        upButton.setOnClickListener(view -> feedRecyclerView.smoothScrollToPosition(0));

        if (!viewModel.isInitialized()) {
            // load first page
            viewModel.loadNews();
            viewModel.setInitialized(true);
        }
    }
}