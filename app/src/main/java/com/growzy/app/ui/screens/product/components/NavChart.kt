package com.growzy.app.ui.screens.product.components

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.growzy.app.data.remote.dto.NavDataDto
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun NavChart(
    navList: List<NavDataDto>
) {

    AndroidView(
        factory = { context ->
            LineChart(context).apply {

                val entries = navList.mapIndexed { index, item ->
                    Entry(index.toFloat(), item.nav.toFloatOrNull() ?: 0f)
                }

                val dataSet = LineDataSet(entries, "NAV History").apply {
                    color = Color.BLUE
                    valueTextColor = Color.BLACK
                    setDrawCircles(false)
                    lineWidth = 2f
                }

                data = LineData(dataSet)
                description.isEnabled = false
                axisRight.isEnabled = false

                invalidate()
            }
        }
    )
}