package test

import com.example.androidcodetest.data.CryptoRepository
import com.example.androidcodetest.data.local.CryptoDao
import com.example.androidcodetest.data.model.CryptoResponse
import com.example.androidcodetest.data.remote.CryptoApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CryptoRepositoryTest {

    @Mock
    lateinit var mockApiService: CryptoApiService
    @Mock lateinit var mockCryptoCacheDao: CryptoDao
    @Mock lateinit var mockCryptoDao: CryptoDao

    lateinit var repository: CryptoRepository

    @Before
    fun setUp() {
        repository = CryptoRepository(mockCryptoDao)
    }

    @Test
    fun testFetchAndStoreCryptos_withValidCache() = runBlocking {

    }

    @Test
    fun testFetchAndStoreCryptos_withNetworkRequest() = runBlocking {
        // Mock API response
        val response = listOf(CryptoResponse("1", "Bitcoin", 50000.0, "BTC", "image_url"))
        Mockito.`when`(mockApiService.getTopCryptos()).thenReturn(response)

        // Fetch data
        repository.fetchAndStoreCryptos()

        // Verify that data was inserted into the cache and database

        Mockito.verify(mockCryptoDao).insertAll(Mockito.anyList())
    }
}
