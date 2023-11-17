package com.techieblossom.kmicroanimations.stacked.column

import androidx.compose.ui.graphics.Color

data class ColumnData(
    val monthName: String,
    val stacks: List<StackData>
)

data class StackData(
    val label: String,
    val value: Double,
)

val dataset = listOf(
    ColumnData(
        "JAN",
        listOf(
            StackData(label = "Food", value = 1500.0),
            StackData(label = "Travel", value = 1000.0),
            StackData(label = "Medical", value = 800.0)
        ),
    ),
    ColumnData(
        "FEB", listOf(
            StackData(label = "Food", value = 500.0),
            StackData(label = "Travel", value = 800.0),
            StackData(label = "Medical", value = 1000.0),
            StackData(label = "Others", value = 1000.0),
        )
    ),
    ColumnData(
        "MAR", listOf(
            StackData(label = "Food", value = 1000.0),
            StackData(label = "Travel", value = 2800.0),
            StackData(label = "Medical", value = 700.0),
            StackData(label = "Others", value = 500.0),
        )
    ),
    ColumnData(
        "APR", listOf(
            StackData(label = "Food", value = 1500.0),
            StackData(label = "Travel", value = 2400.0),
            StackData(label = "Medical", value = 300.0),
            StackData(label = "Others", value = 500.0),
        )
    ),
    ColumnData(
        "MAY", listOf(
            StackData(label = "Food", value = 1200.0),
            StackData(label = "Travel", value = 2000.0),
            StackData(label = "Medical", value = 800.0),
            StackData(label = "Others", value = 400.0),
        )
    ),
    ColumnData(
        "JUN", listOf(
            StackData(label = "Food", value = 2500.0),
            StackData(label = "Travel", value = 500.0),
            StackData(label = "Medical", value = 400.0),
            StackData(label = "Others", value = 600.0),
        )
    ),
    ColumnData(
        "JUL",
        listOf(
            StackData(label = "Food", value = 1500.0),
            StackData(label = "Travel", value = 1000.0),
            StackData(label = "Medical", value = 600.0),
            StackData(label = "Others", value = 300.0),
        ),
    ),
)

val colors = listOf(
    Color(0xFFFF9E80),
    Color(0xFF00897B),
    Color(0xFFF57F17),
    Color(0xFF1565C0),
)