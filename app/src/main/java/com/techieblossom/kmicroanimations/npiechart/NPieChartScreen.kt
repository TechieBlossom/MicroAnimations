package com.techieblossom.kmicroanimations.npiechart

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
fun NPieChartScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(id = R.string.n_pie_animation)) }) }) {
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            AnimatedNPieChart(
                modifier = Modifier
                    .padding(it)
                    .align(Alignment.Center)
                    .size(200.dp),
                pieDataPoints = listOf(
                    PieData("Win", 20, color = Color.Black),
                    PieData("Draw", 10, color = Color.Black.copy(alpha = 0.5f)),
                    PieData("Loss", 10, color = Color.LightGray),
                ),
            )
        }
    }
}