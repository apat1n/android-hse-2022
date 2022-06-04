package com.example.redditfeed.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// TODO: не используется :(
@Database(entities = {RedditNewsItem.class}, version = 1, exportSchema = false)
public abstract class RedditNewsItemDatabase extends RoomDatabase {
    public abstract RedditNewsItemDAO newsDao();
}
