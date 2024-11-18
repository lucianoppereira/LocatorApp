package com.example.locatorapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun favIconButton(isFavorite: Boolean, onFavClick: () -> Unit) {
    val icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    IconButton(onClick = {
        onFavClick.invoke()
    }) {
        Icon(imageVector = icon, contentDescription = "favorite", modifier = Modifier.size(24.dp))
    }
}