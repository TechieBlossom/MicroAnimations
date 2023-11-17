@file:OptIn(ExperimentalMaterial3Api::class)

package com.techieblossom.kmicroanimations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techieblossom.kmicroanimations.distributedbargraph.DistributedBarGraphScreen
import com.techieblossom.kmicroanimations.gappedPieChart.GapPieChartScreen
import com.techieblossom.kmicroanimations.nightingalechart.NightingaleChartScreen
import com.techieblossom.kmicroanimations.npiechart.NPieChartScreen

enum class Routes {
    AnimationsList,
    NPieAnimation,
    DistributedBarGraph,
    GapPieChart,
    NightingaleChart,
}

@Composable
fun MicroAnimationApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.AnimationsList.name,
    ) {
        composable(Routes.AnimationsList.name) { AnimationsList(navController) }
        composable(Routes.NPieAnimation.name) { NPieChartScreen(navController) }
        composable(Routes.DistributedBarGraph.name) { DistributedBarGraphScreen(navController) }
        composable(Routes.GapPieChart.name) { GapPieChartScreen(navController) }
        composable(Routes.NightingaleChart.name) { NightingaleChartScreen(navController) }
    }
}

@Composable
fun AnimationsList(navController: NavHostController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Animations") }) }) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(
                headlineText = { Text(stringResource(id = R.string.n_pie_animation)) },
                modifier = Modifier.clickable {
                    navController.navigate(Routes.NPieAnimation.name)
                },
            )
            ListItem(
                headlineText = { Text(stringResource(id = R.string.gap_pie_chart)) },
                modifier = Modifier.clickable {
                    navController.navigate(Routes.GapPieChart.name)
                },
            )
            ListItem(
                headlineText = { Text(stringResource(id = R.string.distributed_bar_graph)) },
                modifier = Modifier.clickable {
                    navController.navigate(Routes.DistributedBarGraph.name)
                },
            )
            ListItem(
                headlineText = { Text(stringResource(id = R.string.nightingale_chart)) },
                modifier = Modifier.clickable {
                    navController.navigate(Routes.NightingaleChart.name)
                },
            )
        }
    }

}