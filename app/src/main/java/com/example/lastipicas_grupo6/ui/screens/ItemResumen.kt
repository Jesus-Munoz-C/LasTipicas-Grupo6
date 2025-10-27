package com.example.lastipicas_grupo6.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lastipicas_grupo6.model.Producto

@Composable
fun ItemResumen(
    producto: Producto,
    cantidad: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${producto.nombre} ($cantidad)",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$${producto.precio * cantidad}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}