package com.example.androidcodetest.data.model
/**
 * Represents the response model for cryptocurrency data fetched from an API.
 *
 * This data class maps the API response and holds cryptocurrency details.
 */
data class CryptoResponse(
    /**
     * Unique identifier for the cryptocurrency.
     */
    val id: String,

    /**
     * Name of the cryptocurrency (e.g., Bitcoin, Ethereum).
     */
    val name: String,

    /**
     * Current price of the cryptocurrency in USD.
     */
    val current_price: Double,

    /**
     * Symbol of the cryptocurrency (e.g., BTC, ETH).
     */
    val symbol: String,

    /**
     * URL of the cryptocurrency's logo/image.
     */
    val image: String
)

