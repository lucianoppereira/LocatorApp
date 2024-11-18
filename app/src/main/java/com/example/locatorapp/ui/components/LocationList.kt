package com.example.locatorapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.locatorapp.domain.model.LocationModel

@Composable
fun LocationList(
    items: List<LocationModel>,
    visible: Boolean,
    onFavClick: (LocationModel) -> Unit,
    onSelect: (LocationModel) -> Unit
) {
    AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
        LazyColumn {
            items(items) {
                LocationItem(
                    it,
                    onFavClick = { onFavClick.invoke(it) }
                ) {
                    onSelect.invoke(it)
                }
            }
        }
    }
}