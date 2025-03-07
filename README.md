Crypto Application Documentation 
1. CryptoEntity Class 
Purpose: This class represents a cryptocurrency's data in the database. 
Fields: - id: The unique identifier for the cryptocurrency (e.g., "1" for Bitcoin). - name: The name of the cryptocurrency (e.g., "Bitcoin"). - symbol: The short symbol representing the cryptocurrency (e.g., "BTC"). - current_price: The current price of the cryptocurrency (e.g., 50000.0). - image: The URL for the cryptocurrency's logo or image. 
2. CryptoResponse Class 
Purpose: This class represents the response data we get from the API when fetching 
cryptocurrency data. 
Fields: - id: The unique identifier for the cryptocurrency (e.g., "1" for Bitcoin). - name: The name of the cryptocurrency (e.g., "Bitcoin"). - symbol: The short symbol representing the cryptocurrency (e.g., "BTC"). - current_price: The current price of the cryptocurrency (e.g., 50000.0). - image: The URL for the cryptocurrency's logo or image. 
3. CryptoDao Interface 
Purpose: This interface defines how we interact with the local database using Room. 
Methods: - getAllCrypto(): Returns all cryptocurrencies stored in the local database as a LiveData object. - insertAll(): Inserts a list of cryptocurrencies into the database. If a cryptocurrency already 
exists, it replaces it. 
4. CryptoApiService Interface 
Purpose: This interface defines the methods for making network calls to get cryptocurrency 
data from an external API (like CoinGecko). 
Methods: - getTopCryptos(): Fetches the top cryptocurrencies from the API and returns them as a list of 
CryptoResponse objects. 
5. CryptoDatabase Class 
Purpose: This class represents the Room database for storing cryptocurrency data locally. 
Methods: - cryptoDao(): Returns an instance of CryptoDao, which allows interaction with the database 
(fetching and inserting data). - getDatabase(): A singleton method to ensure we only have one instance of the database at a 
t
 ime. 
6. RetrofitClient Object 
Purpose: This object sets up Retrofit and other network-related utilities for making API calls. 
Components: - loggingInterceptor: Used for logging network requests and responses (for debugging 
purposes). - httpClient: The client used to handle network requests, including the logging interceptor. - moshi: A JSON converter used by Retrofit to convert JSON responses into Kotlin data classes. - retrofit: The main Retrofit instance, which defines the base URL, client, and JSON converter. - apiService: The CryptoApiService instance that will be used to call the API methods. 
7. CryptoRepository Class 
Purpose: This class is responsible for managing data from both the network (API) and the local 
database. It handles fetching, storing, and providing the data. 
Fields: - allCryptos: A LiveData object that holds all the cryptocurrencies from the local database. 
Methods: - fetchAndStoreCryptos(): Fetches cryptocurrency data from the API and stores it in the local 
database. 
8. CryptoViewModel Class 
Purpose: This class is responsible for managing UI-related data for the cryptocurrency list. It 
interacts with the CryptoRepository to fetch data. 
Fields: - cryptos: A StateFlow object that holds the list of cryptocurrencies for UI updates. 
Methods: - refreshData(): Fetches the latest cryptocurrency data from the API using the CryptoRepository. 
Initialization: 
Observes changes in the database and updates the `cryptos` field whenever new data is added 
to the database. 
9. CryptoRepositoryTest Class (Unit Test) 
Purpose: This class is used for testing the CryptoRepository class. It mocks the necessary 
components like CryptoDao and CryptoApiService to ensure the repository works correctly. 
Test Cases: 
1. testFetchAndStoreCryptos(): Simulates the process of fetching cryptocurrency data from the 
API and storing it in the database. 
2. testGetAllCryptos(): Ensures that the repository can correctly fetch data from the database 
and return it as a LiveData object. 
10. Important Concepts in the Code 
LiveData: A data holder class that allows observing changes in the data. It’s lifecycle-aware, 
which means it only updates UI components when the activity/fragment is active. 
Room Database: A local database that provides an abstraction layer over SQLite. Room 
simplifies database management by using annotations for entity classes and DAO methods. 
Retrofit: A type-safe HTTP client for Android and Java. It makes it easy to interact with REST APIs 
and convert JSON data into Kotlin objects. 
ViewModel: A lifecycle-aware component used to manage UI-related data in a way that survives 
configuration changes like screen rotations. 
Repository Pattern: A design pattern that acts as a mediator between data sources (API, 
database, etc.) and the rest of the application. 
11. How It All Works Together 
1. API Call: When the app wants to get cryptocurrency data, it calls the 
CryptoRepository.fetchAndStoreCryptos() method. This method makes an API call using Retrofit 
(CryptoApiService.getTopCryptos()) and stores the result in the Room database using 
CryptoDao.insertAll(). 
2. Data Fetching: The CryptoViewModel observes the local database using LiveData 
(CryptoRepository.allCryptos) and updates the UI whenever new data is available. 
3. UI Update: The UI components (like RecyclerView) observe the cryptos field in the 
CryptoViewModel. When data changes, the UI is automatically updated to reflect the latest 
cryptocurrency data. 
4. Error Handling: If any error occurs (e.g., network failure), it’s logged, and appropriate error 
handling can be added to notify the user.
