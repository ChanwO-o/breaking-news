package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create Retrofit singleton class to make requests from anywhere in codebase
 */
class RetrofitInstance {
    companion object {
        private val retrofit by lazy { // lazy: initialize this only once
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) // see body of response
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            // pass client to retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // determine how response should be interpreted as Kotlin object
                .client(client)
                .build()
        }

        // get api instance from retrofit builder (use this anywhere in codebase to make requests)
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}