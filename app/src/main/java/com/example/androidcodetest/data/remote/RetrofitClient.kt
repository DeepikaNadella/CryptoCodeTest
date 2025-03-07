package com.example.androidcodetest.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * RetrofitClient object that sets up Retrofit for network requests.
 *
 * This object is responsible for configuring and creating the Retrofit instance that communicates
 * with the CoinGecko API, handles logging, and converts JSON data into Kotlin objects.
 */
object RetrofitClient {

    // Set up an interceptor for logging HTTP request and response details.
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs the request/response body
    }

    // Create an HTTP client with the logging interceptor added.
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add the interceptor to log requests/responses
        .build()

    // Moshi is used to handle JSON parsing. This sets up Moshi with the Kotlin adapter.
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // This line enables Moshi to work with Kotlin data classes
        .build()

    // Create the Retrofit instance, which is used for making API requests.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/") // Set the base URL for the API
        .client(httpClient) // Use the HTTP client that includes the logging interceptor
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Set Moshi as the converter to parse JSON
        .build()

    // Create and expose the API service, which allows interaction with the CoinGecko API.
    val apiService: CryptoApiService = retrofit.create(CryptoApiService::class.java)
}
