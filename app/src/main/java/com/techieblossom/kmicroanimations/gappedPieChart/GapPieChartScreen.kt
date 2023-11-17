package com.techieblossom.kmicroanimations.gappedPieChart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techieblossom.kmicroanimations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GapPieChartScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(id = R.string.gap_pie_chart)) }) }) {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            AnimatedGapPieChart(
                modifier = Modifier
                    .padding(it)
                    .align(Alignment.Center)
                    .size(200.dp),
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