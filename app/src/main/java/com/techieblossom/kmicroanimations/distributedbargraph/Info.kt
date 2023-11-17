package com.techieblossom.kmicroanimations.distributedbargraph

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun Info(currentCoordinates: IntOffset?, info: String, color: Color) {
    Popup(
        popupPositionProvider = StickCenterOffsetPositionProvider(
            x = currentCoordinates?.x?.minus(with(LocalDensity.current) { 16.dp.toPx().toInt() }) ?: 0,
            y = currentCoordinates?.y ?: 0
        ),
    ) {
        Text(
            info,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .border(color = color, width = 4.dp, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}