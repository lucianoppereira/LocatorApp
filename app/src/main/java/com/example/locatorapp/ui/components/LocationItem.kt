package com.example.locatorapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
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
fun LocationItem(location: LocationModel, onFavClick: () -> Unit, onSelect: () -> Unit) {
    Card(
        modifier = Modifier.padding(16.dp, 8.dp),
        onClick = { onSelect.invoke() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(5f, true)) {
                Text(
                    text = location.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(top = 8.dp)
                )

                Text(
                    text = location.subtitle,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f, false)

            ) {
                favIconButton(isFavorite = location.isFavorite) {
                    onFavClick.invoke()
                }
            }
        }
    }
}