package com.example.androidcodetest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidcodetest.data.model.CryptoEntity

/**
 * Room database class for storing cryptocurrency data.
 *
 * This class defines the database structure and provides access to the DAO (Data Access Object).
 */
@Database(entities = [CryptoEntity::class], version = 4, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    /**
     * Provides access to the DAO for database operations.
     */
    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoDatabase? = null

        /**
         * Returns an instance of the database.
         *
         * Ensures that only a single instance of the database is created (Singleton pattern).
         * If the database does not exist, it is built using Room.
         *
         * @param context Application context.
         * @return An instance of [CryptoDatabase].
         */
        fun getDatabase(context: Context): CryptoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoDatabase::class.java,
                    "crypto_database"
                )
                    .fallbackToDestructiveMigration() // Deletes and recreates the database if a version change occurs without migration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
