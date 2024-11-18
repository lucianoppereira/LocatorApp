package com.example.locatorapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.locatorapp.domain.model.LocationModel

@Composable
fun LocationInfo(location: LocationModel?, onFavClick: () -> Unit, onClose: () -> Unit) {
    AnimatedVisibility(
        visible = location != null,
        enter = expandIn(),
        exit = shrinkOut()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Bottom)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    favIconButton(location?.isFavorite ?: false) {
                        onFavClick.invoke()
                    }

                    IconButton(onClick = { onClose.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close info"
                        )
                    }
                }

                Text(
                    text = location?.title ?: "",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 4.dp)
                )

                Text(
                    text = location?.subtitle ?: "",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }
        }
    }
}