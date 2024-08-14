package com.namnp.jetpack_compose.chart

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType

@Composable
fun BarChartScreen() {
    val barData = remember {
        DataUtils.getBarChartData(
            listSize = 8,
            maxRange = 8,
            barChartType = BarChartType.VERTICAL,
            dataCategoryOptions = DataCategoryOptions()
        )
    }
    val steps = 5

    val xAxisData = AxisData.Builder()
        .axisStepSize(80.dp)
        .backgroundColor(Color.Transparent)
        .steps(steps - 1)
        .labelData { i -> barData[i].label }
        .axisLabelAngle(20f)
        .bottomPadding(32.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .backgroundColor(Color.Transparent)
        .steps(steps)
        .labelData { i ->
            val yScale = 100f / steps
            (i * yScale).toString()
        }
        .labelAndAxisLinePadding(16.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .axisOffset(20.dp)
        .build()

    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    BarChart(
        modifier = Modifier
            .height(320.dp),
        barChartData = barChartData
    )
}