package com.example.redditfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditfeed.db.RedditNewsItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RedditNewsAdapter extends RecyclerView.Adapter<RedditNewsAdapter.RedditNewsViewHolder> {
    private final List<RedditNewsItem> news;
    private final Context context;

    protected RedditNewsAdapter(Context context, ObservableList<RedditNewsItem> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public RedditNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new RedditNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedditNewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public List<RedditNewsItem> getNews() {
        return news;
    }

    public void addNews(List<RedditNewsItem> news) {
        int prevSize = this.news.size();
        this.news.addAll(news);
        notifyItemRangeInserted(prevSize, news.size());
    }

    public void openURL(int position) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(news.get(position).getUrl()));
        context.startActivity(i);
    }

    class RedditNewsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView thumbnailView;
        private final TextView descriptionView;
        private final TextView authorView;
        private final TextView commentsView;
        private final TextView timeView;

        public RedditNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailView = itemView.findViewById(R.id.img_thumbnail);
            descriptionView = itemView.findViewById(R.id.description);
            authorView = itemView.findViewById(R.id.author);
            commentsView = itemView.findViewById(R.id.comments);
            timeView = itemView.findViewById(R.id.time);
        }

        private void bind(int position) {
            RedditNewsItem item = news.get(position);
            Picasso.get().load(item.getThumbnail()).into(thumbnailView, new Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    thumbnailView.setVisibility(View.GONE);
                }
            });
            descriptionView.setText(item.getTitle());
            authorView.setText("by " + item.getAuthor());
            commentsView.setText(item.getNumComments() + " comments");
            timeView.setText("at " + getBeautifulTime(item.getCreated()));
        }

        private String getBeautifulTime(Long createdTimestamp) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            return sf.format(new Date(createdTimestamp * 1000));
        }
    }
}
