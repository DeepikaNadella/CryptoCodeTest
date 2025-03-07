package com.example.androidcodetest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidcodetest.data.model.CryptoEntity
/**
 * Data Access Object (DAO) for accessing cryptocurrency data in the local database.
 *
 * This interface defines methods for retrieving and inserting cryptocurrency data
 * using Room persistence library.
 */
@Dao
interface CryptoDao {

    /**
     * Retrieves all cryptocurrency records from the database.
     *
     * This method returns a LiveData list of [CryptoEntity] objects,
     * enabling real-time updates in the UI whenever the database changes.
     *
     * @return LiveData<List<CryptoEntity>> containing all stored cryptocurrencies.
     */
    @Query("SELECT * FROM crypto")
    fun getAllCrypto(): LiveData<List<CryptoEntity>>

    /**
     * Inserts a list of cryptocurrency records into the database.
     *
     * If a record with the same primary key already exists, it will be replaced.
     * This ensures that the database remains up-to-date with the latest data.
     *
     * @param cryptos A list of [CryptoEntity] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptos: List<CryptoEntity>)
}


