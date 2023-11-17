package com.techieblossom.kmicroanimations.stacked.column

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme

data class StickData(
    val color: Color,
    val height: Float,
    val animation: Animatable<Float, AnimationVector1D>,
)

@Composable
fun Stick(
    modifier: Modifier = Modifier,
    height: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .height(height)
            .width(20.dp)
    )
}

@Preview
@Composable
fun Preview_Stick() {
    MicroAnimationsTheme {
        Surface {
            Stick(height = 200.dp, color = Color.Gray, modifier = Modifier.padding(24.dp))
        }
    }
}