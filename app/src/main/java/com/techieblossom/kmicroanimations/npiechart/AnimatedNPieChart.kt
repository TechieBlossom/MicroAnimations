package com.techieblossom.kmicroanimations.npiechart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme
import kotlinx.coroutines.launch

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val targetSweepAngle: Float,
    val color: Color,
)

@Composable
fun AnimatedNPieChart(
    modifier: Modifier = Modifier,
    pieDataPoints: List<PieData>,
) {
    val localModifier = modifier.size(200.dp)
    val total = pieDataPoints.fold(0f) { acc, pieData ->
        acc + pieData.value
    }.div(360)
    var currentSum = 0
    val arcs = pieDataPoints.map {
        currentSum += it.value
        ArcData(
            targetSweepAngle = currentSum / total,
            animation = Animatable(0f),
            color = it.color
        )
    }

    LaunchedEffect(arcs) {
        arcs.map {
            launch {
                it.animation.animateTo(
                    targetValue = it.targetSweepAngle,
                    animationSpec = tween(
                        durationMillis = 4000,
                        easing = FastOutSlowInEasing,
                    ),
                )
            }
        }
    }

    Canvas(
        modifier = localModifier
            .scale(1f)
    ) {
        val stroke = Stroke(width = 20f)

        arcs.reversed().map {
            drawArc(
                startAngle = -90f,
                sweepAngle = it.animation.value,
                color = it.color,
                useCenter = false,
                style = stroke
            )
        }
    }
    Row(
        modifier = localModifier
            .fillMaxSize()
            .padding(16.dp)
            .scale(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        pieDataPoints.map {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = it.label, fontWeight = FontWeight.W600)
                Text(text = it.value.toString())
            }
        }
    }
}

@Preview
@Composable
fun Preview_AnimatedNPieChart() {
    MicroAnimationsTheme {
        Surface {
            AnimatedNPieChart(
                modifier = Modifier.padding(32.dp),
                pieDataPoints = listOf(
                    PieData("Win", 20, color = Color.Black),
                    PieData("Draw", 10, color = Color.Black.copy(alpha = 0.5f)),
                    PieData("Loss", 10, color = Color.LightGray),
                ),
            )
        }
    }
}