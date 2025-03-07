package com.example.androidcodetest.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.androidcodetest.viewmodel.CryptoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androidcodetest.data.model.CryptoEntity


@Composable
fun CryptoScreen(viewModel: CryptoViewModel) {
    val cryptos by viewModel.cryptos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { viewModel.refreshData() }) {
            Text("Refresh Prices")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(cryptos) { crypto ->
                CryptoItem(crypto)
            }
        }
    }
}



@Composable
fun CryptoItem(crypto: CryptoEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Load image using Coil
            AsyncImage(
                model = crypto.image,
                contentDescription = "${crypto.name} Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = crypto.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "${crypto.symbol.uppercase()} - $${crypto.current_price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/* @Composable
    fun CryptoItem(crypto: CryptoEntity) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Load image using Coil
                    AsyncImage(
                        model = crypto.image,
                        contentDescription = "${crypto.name} Logo",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(text = crypto.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "  ${crypto.current_price.formatPrice()}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "0.072 ${crypto.symbol.uppercase()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$45,788", // Replace with actual data
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$2,714", // Replace with actual data
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }

    // Helper function for price formatting
    fun Double.formatPrice(): String {
        return "$" + String.format("%,.2f", this)
    }
*/