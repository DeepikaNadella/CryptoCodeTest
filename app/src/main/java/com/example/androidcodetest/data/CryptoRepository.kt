package com.example.androidcodetest.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.androidcodetest.data.local.CryptoDao
import com.example.androidcodetest.data.model.CryptoEntity
import com.example.androidcodetest.data.remote.RetrofitClient

/**
 * CryptoRepository is responsible for managing data operations related to cryptocurrency.
 *
 * It fetches cryptocurrency data from a remote API and stores it in a local database
 * using the provided CryptoDao. It also provides access to the stored data.
 */
class CryptoRepository(private val cryptoDao: CryptoDao) {

    // LiveData that holds the list of all cryptos stored in the local database.
    // The data can be observed for any changes.
    val allCryptos: LiveData<List<CryptoEntity>> = cryptoDao.getAllCrypto()

    /**
     * Fetches the latest cryptocurrency data from the API and stores it in the local database.
     *
     * It makes a network request to get the top 5 cryptocurrencies, then transforms the data
     * into CryptoEntity objects, and stores them in the local database using CryptoDao.
     */
    suspend fun fetchAndStoreCryptos() {
        try {
            // Fetching the top 5 cryptocurrencies from the remote API
            val response = RetrofitClient.apiService.getTopCryptos()

            // Transforming the API response into CryptoEntity objects
            val entities = response.map { CryptoEntity(it.id, it.name, it.symbol, it.current_price, it.image) }

            // Logging the fetched data for debugging purposes
            Log.d("CryptoRepository", "Fetched Cryptos: $entities")

            // Storing the fetched cryptocurrency data into the local database
            cryptoDao.insertAll(entities)
        } catch (e: Exception) {
            // Logging the error in case of failure during the data fetching process
            Log.e("CryptoRepository", "Error fetching cryptos: ${e.message}")
        }
    }
}


