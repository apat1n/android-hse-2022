package com.example.redditfeed.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RedditNewsItem {
    @PrimaryKey
    private final String url;

    @ColumnInfo(name = "name")
    private final String author;

    private final String title;
    private final Integer numComments;
    private final Long created;
    private final String thumbnail;

    public RedditNewsItem(String url, String author, String title, Integer numComments, Long created, String thumbnail) {
        this.url = url;
        this.author = author;
        this.title = title;
        this.numComments = numComments;
        this.created = created;
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Long getCreated() {
        return created;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
