package com.example.androidcodetest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.androidcodetest.data.CryptoRepository
import com.example.androidcodetest.data.local.CryptoDatabase
import com.example.androidcodetest.data.model.CryptoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * CryptoViewModel manages the UI-related data for displaying cryptocurrency information.
 *
 * It interacts with the CryptoRepository to fetch and store cryptocurrency data, and provides
 * the data to the UI using StateFlow.
 */
class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    // The repository that handles data operations (fetching from API and storing in database)
    private val repository: CryptoRepository

    // A mutable StateFlow that holds the list of cryptocurrencies.
    // It's updated with new data fetched from the repository.
    private val _cryptos = MutableStateFlow<List<CryptoEntity>>(emptyList())

    // A public immutable StateFlow that exposes the list of cryptocurrencies to the UI.
    // The UI observes this flow for updates.
    val cryptos = _cryptos.asStateFlow()

    // A mutable StateFlow that holds an error message, if any.
    private val _errorMessage = MutableStateFlow<String?>(null)

    // A public immutable StateFlow that exposes error messages to the UI.
    val errorMessage = _errorMessage.asStateFlow()

    /**
     * Initialize the ViewModel by setting up the repository and observing the database data.
     *
     * It also triggers the initial data fetch from the repository.
     */
    init {
        // Getting the DAO instance from the CryptoDatabase
        val dao = CryptoDatabase.getDatabase(application).cryptoDao()

        // Initializing the repository with the DAO
        repository = CryptoRepository(dao)

        // Launching a coroutine to observe the LiveData from the database and collect updates
        // When the list of cryptos is updated in the database, it updates the _cryptos StateFlow.
        viewModelScope.launch {
            repository.allCryptos.asFlow().collect { list ->
                _cryptos.value = list ?: emptyList() // If list is null, set it to an empty list
                Log.d("CryptoViewModel", "Updated cryptos in ViewModel: $_cryptos")
            }
        }

        // Fetch and store cryptocurrency data initially
        refreshData()
    }

    /**
     * Triggers a fetch operation to get the latest cryptocurrency data from the API
     * and store it in the local database.
     *
     * In case of an error, it will update the _errorMessage StateFlow to display the error to the UI.
     */
    fun refreshData() {
        // Launching a coroutine on the IO dispatcher (since network and database operations are involved)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Attempt to fetch and store cryptocurrency data from the API
                repository.fetchAndStoreCryptos()

                // If successful, clear any previous error message
                _errorMessage.value = null
            } catch (e: Exception) {
                // If an error occurs, set the error message in _errorMessage StateFlow
                _errorMessage.value = "Failed to fetch cryptocurrency data: ${e.message}"
                Log.e("CryptoViewModel", "Error fetching data: ${e.message}", e)
            }
        }
    }
}




