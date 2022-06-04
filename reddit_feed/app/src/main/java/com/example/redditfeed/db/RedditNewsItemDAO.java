package com.example.redditfeed.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RedditNewsItemDAO {
    @Query("SELECT * FROM redditnewsitem")
    DataSource.Factory<Integer, RedditNewsItem> news();
}
