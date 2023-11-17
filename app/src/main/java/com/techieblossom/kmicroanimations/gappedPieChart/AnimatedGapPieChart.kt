package com.techieblossom.kmicroanimations.gappedPieChart//package com.techieblossom.kmicroanimations.gappedPieChart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme
import kotlinx.coroutines.launch

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val targetSweepAngle: Float,
    val startAngle: Float,
    val color: Color,
)

@Composable
fun AnimatedGapPieChart(
    modifier: Modifier = Modifier,
    pieDataPoints: List<PieData>,
) {
    val gapDegrees = 16f
    val numberOfGaps = pieDataPoints.size
    val remainingDegrees = 360f - (gapDegrees * numberOfGaps)
    val localModifier = modifier.size(200.dp)
    val total = pieDataPoints.fold(0f) { acc, pieData -> acc + pieData.value }.div(remainingDegrees)
    var currentSum = 0f
    val arcs = pieDataPoints.mapIndexed { index, it ->
        val startAngle = currentSum + (index * gapDegrees)
        currentSum += it.value / total
        ArcData(
            targetSweepAngle = it.value / total,
            animation = Animatable(0f),
            startAngle = -90 + startAngle,
            color = it.color
        )
    }

    LaunchedEffect(arcs) {
        arcs.mapIndexed { index, it ->
            launch {
                it.animation.animateTo(
                    targetValue = it.targetSweepAngle,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = LinearEasing,
                        delayMillis = index * 1000
                    ),
                )
            }
        }
    }

    Canvas(
        modifier = localModifier
            .scale(1f)
    ) {
        val stroke = Stroke(width = 50f, cap = StrokeCap.Round)

        arcs.map {
            drawArc(
                startAngle = it.startAngle,
                sweepAngle = it.animation.value,
                color = it.color,
                useCenter = false,
                style = stroke,
            )
        }
    }
}

@Preview
@Composable
fun Preview_GapPieChart() {
    MicroAnimationsTheme {
        Surface {
            AnimatedGapPieChart(
                modifier = Modifier.padding(32.dp),
                pieDataPoints = listOf(
                    PieData(30, color = Color(0xFF9400D3)),
                    PieData( 20, color = Color(0xFF42A1D5)),
                    PieData( 15, color = Color(0xFF8D9311)),
                    PieData( 15, color = Color(0xFFFF7F00)),
                    PieData( 20, color = Color(0xFF47A4CF)),
                ),
            )
        }
    }
}