package com.growzy.app.ui.screens.product.components

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.growzy.app.data.remote.dto.NavDataDto
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun NavChart(
    navList: List<NavDataDto>
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val surfaceColor = MaterialTheme.colorScheme.surface

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        factory = { context ->
            LineChart(context).apply {
                val sampledList = if (navList.size > 200) {
                    navList.filterIndexed { index, _ ->
                        index % (navList.size / 200 + 1) == 0
                    }
                } else {
                    navList
                }

                val entries = sampledList.mapIndexed { index, item ->
                    Entry(index.toFloat(), item.nav.toFloatOrNull() ?: 0f)
                }

                val dataSet = LineDataSet(entries, "NAV").apply {
                    color = primaryColor.hashCode()
                    valueTextColor = AndroidColor.TRANSPARENT
                    setDrawCircles(false)
                    setDrawFilled(true)
                    fillColor = primaryColor.hashCode()
                    fillAlpha = 20
                    lineWidth = 3f
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                    cubicIntensity = 0.2f
                }

                data = LineData(dataSet)

                description.isEnabled = false
                legend.isEnabled = false
                axisRight.isEnabled = false

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = onSurfaceColor.hashCode()
                    textSize = 10f
                    labelCount = 5
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()
                            return if (index >= 0 && index < sampledList.size) {
                                sampledList[index].date.takeLast(5)
                            } else ""
                        }
                    }
                }

                axisLeft.apply {
                    setDrawGridLines(true)
                    gridColor = onSurfaceColor.hashCode()
                    gridLineWidth = 0.5f
                    textColor = onSurfaceColor.hashCode()
                    textSize = 10f
                }

                setBackgroundColor(AndroidColor.TRANSPARENT)
                setTouchEnabled(true)
                setDragEnabled(true)
                setScaleEnabled(true)
                setPinchZoom(false)

                invalidate()
            }
        },
        update = { chart ->
            chart.data.dataSets.first().let { dataSet ->
                (dataSet as LineDataSet).color = primaryColor.hashCode()
                dataSet.fillColor = primaryColor.hashCode()
            }
            chart.xAxis.textColor = onSurfaceColor.hashCode()
            chart.axisLeft.textColor = onSurfaceColor.hashCode()
            chart.axisLeft.gridColor = onSurfaceColor.hashCode()
            chart.invalidate()
        }
    )
}