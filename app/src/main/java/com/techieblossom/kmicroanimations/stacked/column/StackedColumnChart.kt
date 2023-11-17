package com.techieblossom.kmicroanimations.stacked.column

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme
import kotlinx.coroutines.launch

@Composable
fun StackedColumnChart(modifier: Modifier = Modifier, data: List<ColumnData>) {
    val maxAmount = data.fold(0.0) { acc, columnData ->
        val monthSum = columnData.stacks.sumOf {
            it.value
        }
        if (monthSum > acc) monthSum else acc
    }
    val localDensity = LocalDensity.current
    val heightInFloat = with(localDensity) { 300.dp.toPx() }

    val sticks = remember {
        data.map {
            it.stacks.mapIndexed { index, stackData ->
                StickData(
                    height =
                    stackData.value.div(maxAmount).times(heightInFloat).toFloat(),
                    color = colors[index],
                    animation = Animatable(0f),
                )
            }
        }
    }

    LaunchedEffect(key1 = sticks) {
        sticks.map {
            launch {
                it.map { stickData ->
                    stickData.animation.animateTo(
                        targetValue = stickData.height,
                        animationSpec = tween(
                            durationMillis = (stickData.height * 5).toInt(),
                            easing = LinearEasing
                        )
                    )
                }
            }
        }
    }

    Row(modifier = modifier) {
        sticks.mapIndexed { index, localSticks ->
            Column(
                modifier = Modifier
                    .height(320.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                localSticks.reversed().map {
                    val heightInDp = with(localDensity) { it.animation.value.toDp() }
                    Stick(height = heightInDp, color = it.color)
                }
                Text(
                    text = data[index].monthName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_StackedColumnChart() {
    MicroAnimationsTheme {
        Surface {
            StackedColumnChart(
                data = dataset, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}