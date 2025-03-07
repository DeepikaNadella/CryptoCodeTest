package com.example.androidcodetest.data.remote

import com.example.androidcodetest.data.model.CryptoResponse
import retrofit2.http.GET

/**
 * Interface that defines the API endpoints for fetching cryptocurrency data.
 *
 * This service interacts with the CoinGecko API to retrieve market data related to cryptocurrencies.
 */
interface CryptoApiService {

    /**
     * Fetches the top 5 cryptocurrencies in USD based on market capitalization.
     *
     * The API request is configured to fetch the top 5 cryptocurrencies, ordered by market cap,
     * and does not include any sparkline data for each coin.
     *
     * @return A list of [CryptoResponse] containing the cryptocurrency details such as name,
     *         symbol, current price, and image URL.
     */
    @GET("v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=5&page=1&sparkline=false")
    suspend fun getTopCryptos(): List<CryptoResponse>
}
