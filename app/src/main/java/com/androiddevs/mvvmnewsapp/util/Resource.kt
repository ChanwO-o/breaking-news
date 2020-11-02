package com.androiddevs.mvvmnewsapp.util

/**
 * Recommended to wrap around network responses
 * Success / Error responses
 * Handle loading states
 */
sealed class Resource<T>( // sealed: abstract, but decide which classes are allowed to inherit from this class
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}