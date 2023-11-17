package com.techieblossom.kmicroanimations.distributedbargraph

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme
import kotlinx.coroutines.launch

data class StickData(
    val color: Color,
    val animation: Animatable<Float, AnimationVector1D>,
    val targetHeight: Double,
    val info: String,
)

@Composable
fun BarGraph(dataset: List<BarData>, modifier: Modifier = Modifier) {
    val maxAmount = dataset.fold(0.0) { acc, barData ->
        val monthSum = barData.food + barData.medical + barData.travel + barData.others
        if (acc > monthSum) acc else monthSum
    }
    val localDensity = LocalDensity.current
    val heightInFloat = with(localDensity) { 300.dp.toPx() }
    var isInfoShown by remember { mutableStateOf(false) }
    var infoOffset by remember { mutableStateOf(IntOffset.Zero) }
    var infoColor by remember { mutableStateOf(Color.Transparent) }
    var info by remember { mutableStateOf("") }
    val sticks = remember {
        dataset.map {
            listOf(
                StickData(
                    color = Color(0xFFF57F17),
                    animation = Animatable(0f),
                    targetHeight = (it.food / maxAmount).times(heightInFloat),
                    info = createInfoString("Food", it.food)
                ),
                StickData(
                    color = Color(0xFF00897B),
                    animation = Animatable(0f),
                    targetHeight = (it.travel / maxAmount).times(heightInFloat),
                    info = createInfoString("Travel", it.travel)
                ),
                StickData(
                    color = Color(0xFF1565C0),
                    animation = Animatable(0f),
                    targetHeight = (it.medical / maxAmount).times(heightInFloat),
                    info = createInfoString("Medical", it.medical)
                ),
                StickData(
                    color = Color(0xFFFF9E80),
                    animation = Animatable(0f),
                    targetHeight = (it.others / maxAmount).times(heightInFloat),
                    info = createInfoString("Others", it.others)
                ),
            )
        }
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LaunchedEffect(key1 = sticks) {
            sticks.map {
                launch {
                    it.map { stickData ->
                        stickData.animation.animateTo(
                            targetValue = stickData.targetHeight.toFloat(),
                            animationSpec = tween(
                                durationMillis = (stickData.targetHeight * 5).toInt(),
//                                durationMillis = 300,
                                easing = LinearEasing,
                            )
                        )
                    }
                }
            }
        }

        val currentCoordinates = remember { mutableStateMapOf<String, IntOffset>() }

        sticks.mapIndexed { index, localSticks ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(320.dp)
                    .align(Alignment.Bottom),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    localSticks.reversed().mapIndexed { localIndex, stickData ->
                        val id = index.toString() + localIndex.toString()
                        Stick(
                            height = with(localDensity) { stickData.animation.value.toDp() },
                            color = stickData.color,
                            modifier = Modifier
                                .onGloballyPositioned {
                                    currentCoordinates[id] =
                                        with(it.positionInRoot()) {
                                            IntOffset(
                                                x.toInt(),
                                                y.toInt()
                                            )
                                        }
                                }
                                .pointerInput(id) {
                                    detectTapGestures {
                                        isInfoShown = true
                                        infoOffset = currentCoordinates.getOrDefault(
                                            id,
                                            IntOffset.Zero
                                        ) + IntOffset(
                                            0,
                                            it.y.toInt()
                                        )
                                        info = stickData.info
                                        infoColor = stickData.color
                                    }
                                }
                        )
                    }
                }
                Label(text = dataset[index].monthName)
            }
        }

        if (isInfoShown) {
            Info(currentCoordinates = infoOffset, info = info, color = infoColor)
        }
    }
}

private fun createInfoString(category: String, amount: Double) = buildString {
    append(category)
    appendLine()
    append(amount.toString())
}

@Preview
@Composable
fun Preview_BarGraph() {
    MicroAnimationsTheme {
        Surface {
            BarGraph(
                dataset = dataset,
                modifier = Modifier.padding(24.dp).fillMaxWidth()
            )
        }
    }
}