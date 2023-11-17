package com.techieblossom.kmicroanimations.nightingalechart

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
fun NightingaleChartScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(id = R.string.n_pie_animation)) }) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AnimatedNightingaleChart(
                modifier = Modifier
                    .padding(it)
                    .align(Alignment.Center)
                    .size(200.dp),
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