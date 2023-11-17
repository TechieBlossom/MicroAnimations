package com.techieblossom.kmicroanimations.nightingalechart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme
import kotlinx.coroutines.launch

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val sweepAngle: Float,
    val startAngle: Float,
    val radius: Float,
    val color: Color,
)

@Composable
fun AnimatedNightingaleChart(
    modifier: Modifier = Modifier,
    pieDataPoints: List<PieData>,
) {
    val localModifier = modifier.size(200.dp)
    val total = pieDataPoints.fold(0f) { acc, pieData -> acc + pieData.value }.div(360)
    val maxValue =
        pieDataPoints.fold(0) { acc, pieData -> if (acc > pieData.value) acc else pieData.value }
    val sweepAngle = 360f / pieDataPoints.size
    var currentSum = 0f
    val arcs = pieDataPoints.map {
        val startAngle = currentSum
        currentSum += sweepAngle
        ArcData(
            startAngle = -90 + startAngle,
            sweepAngle = sweepAngle,
            animation = Animatable(0f),
            color = it.color,
            radius = (it.value / maxValue).toFloat() * 100,
        )
    }

    LaunchedEffect(arcs) {
        arcs.map {
            launch {
                it.animation.animateTo(
                    targetValue = it.radius,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing,
                    ),
                )
            }
        }
    }

    Canvas(modifier = localModifier) {
        val stroke = Stroke(
            pathEffect = PathEffect.cornerPathEffect(10f),
            width = 10f
        )

        arcs.map {
            drawArc(
                startAngle = it.startAngle,
                sweepAngle = it.sweepAngle,
                color = it.color,
                useCenter = true,
                style = stroke,
            )
        }
//        drawArc(
//            startAngle = -90f,
//            sweepAngle = 60f,
//            color = Color.Black,
//            useCenter = true,
//            style = stroke,
//            topLeft = Offset(10f, 10f)
//        )
//        drawArc(
//            startAngle = -90f + 60f,
//            sweepAngle = 60f,
//            color = Color.Gray,
//            useCenter = true,
//            style = stroke,
//        )
    }
}

@Preview
@Composable
fun Preview_AnimatedNPieChart() {
    MicroAnimationsTheme {
        Surface {
            AnimatedNightingaleChart(
                modifier = Modifier.padding(16.dp),
                pieDataPoints = listOf(
                    PieData("Vanilla", 20, color = Color(0xFFFEF1C2)),
                    PieData("Chocolate", 18, color = Color(0xFF99653D)),
                    PieData("Caramel", 16, color = Color(0xFFE0AD1E)),
                    PieData("Pistachio", 10, color = Color(0xFFBDC94C)),
                    PieData("Coffee", 12, color = Color(0xFF572C02)),
                    PieData("Lemon", 14, color = Color(0xFFF2EC14)),
                    PieData("Mango", 9, color = Color(0xFFFFC41E)),
                    PieData("Passion Fruit", 8, color = Color(0xFF822B46)),
                    PieData("Raspberry", 6, color = Color(0xFFF94B4A)),
                    PieData("Other", 5, color = Color(0xFFC2B2A0)),
                ),
            )
        }
    }
}