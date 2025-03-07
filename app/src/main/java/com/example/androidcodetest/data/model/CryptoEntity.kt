package com.example.androidcodetest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a cryptocurrency entity for the local database.
 *
 * This data class is used to store cryptocurrency details in the Room database.
 */
@Entity(tableName = "crypto")
data class CryptoEntity(
    /**
     * Unique identifier for the cryptocurrency (Primary Key).
     */
    @PrimaryKey val id: String,

    /**
     * Name of the cryptocurrency (e.g., Bitcoin, Ethereum).
     */
    val name: String,

    /**
     * Symbol of the cryptocurrency (e.g., BTC, ETH).
     */
    val symbol: String,

    /**
     * Current price of the cryptocurrency in USD.
     */
    val current_price: Double,

    /**
     * URL of the cryptocurrency's logo/image.
     */
    val image: String
)
