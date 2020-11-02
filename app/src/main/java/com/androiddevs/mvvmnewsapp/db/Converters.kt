package com.androiddevs.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.models.Source

// convert data from database to object, or vice versa
class Converters {
    // convert source to a string
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name // return name of source, don't care about id
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}