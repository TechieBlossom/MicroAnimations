package com.techieblossom.kmicroanimations.distributedbargraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Stick(
    modifier: Modifier = Modifier,
    height: Dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .width(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = color)
            .height(height)
    )
}