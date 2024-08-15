package com.namnp.jetpack_compose.chart

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun PieChartScreen() {
    val pieCharData = remember {
        PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    label = "Android",
                    value = 65f,
                    color = Color.Blue
                ),
                PieChartData.Slice(
                    label = "iOS",
                    value = 25f,
                    color = Color.Yellow
                ),
                PieChartData.Slice(
                    label = "Flutter",
                    value = 8f,
                    color = Color.Green
                ),
                PieChartData.Slice(
                    label = "Others",
                    value = 2f,
                    color = Color.Red
                )
            ),
            plotType = PlotType.Pie // Bar, Donut
        )
    }

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = false,
        activeSliceAlpha = 0.5f,
        animationDuration = 500,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    PieChart(
        modifier = Modifier
            .size(320.dp),
        pieChartData = pieCharData,
        pieChartConfig = pieChartConfig
    )
}