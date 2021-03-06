package com.androiddevs.mvvmnewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// defines a table row in database
@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true) // autoGenerate: handles auto increment of id
    var id: Int? = null, // not every article will have an id, some will be from Retrofit that we don't save into database
    val author: String?, // same for every other attribute, null-checks
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable