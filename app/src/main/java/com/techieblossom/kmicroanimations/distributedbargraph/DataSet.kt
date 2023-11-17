package com.techieblossom.kmicroanimations.distributedbargraph

data class BarData(
    val monthName: String,
    val food: Double,
    val medical: Double,
    val travel: Double,
    val others: Double,
)

val dataset = listOf(
    BarData("JAN", 1500.0, 1000.0, 800.0, 0.0),
    BarData("FEB", 500.0, 800.0, 1000.0, 1000.0),
    BarData("MAR", 1000.0, 2800.0, 700.0, 500.0),
    BarData("APR", 1500.0, 2400.0, 300.0, 500.0),
    BarData("MAY", 1200.0, 2000.0, 800.0, 400.0),
    BarData("JUN", 2500.0, 500.0, 400.0, 600.0),
    BarData("JUL", 1500.0, 1000.0, 600.0, 300.0),
)
