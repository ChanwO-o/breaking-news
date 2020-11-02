package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article

@Dao // tell Room that this interface defines Dao functions
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // onConflict: inserted article already exists. REPLACE: replace existing article
    suspend fun upsert(article: Article): Long // upsert: update or insert.

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>> // not a suspend function; this one returns LiveData obj

    @Delete
    suspend fun deleteArticle(article: Article)
}