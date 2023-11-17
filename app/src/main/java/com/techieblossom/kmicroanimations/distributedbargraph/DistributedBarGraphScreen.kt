package com.techieblossom.kmicroanimations.distributedbargraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.techieblossom.kmicroanimations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistributedBarGraphScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(id = R.string.distributed_bar_graph)) }) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            BarGraph(dataset = dataset, modifier = Modifier.padding(it))
        }
    }
}

@Preview
@Composable
fun Preview_Screen() {
    DistributedBarGraphScreen(navController = rememberNavController())
}