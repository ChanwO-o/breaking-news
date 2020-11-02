package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article

// database classes for Room must be abstract
@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class) // tie custom converter to db
abstract class ArticleDatabase : RoomDatabase() {

    // created database instance will use this to access database functions
    abstract fun getArticleDao(): ArticleDao // Room will handle implementation

    companion object {
        @Volatile // other threads can instantly see when a thread changes this instance
        private var instance: ArticleDatabase? = null
        private var LOCK = Any() // synchronize setting the instance; allow only single instance of database at once

        // called when ArticleDatabase object is instantiated (constructor is called)
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) { // set to instance, but if it's null run this sync block
            // synchronized block: access to one thread at a time
            instance ?: createDatabase(context).also { instance = it } // null-check instance again before creating database
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}