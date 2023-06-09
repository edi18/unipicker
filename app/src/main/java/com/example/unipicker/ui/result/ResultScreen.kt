package com.example.unipicker.ui.result

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ResultScreen(
    navigateToNextScreen: () -> Unit,
    results: MutableList<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val rez = arrayOf(
            "Društvene nauke" to results[0],
            "Humanističke nauke" to results[1],
            "Medicinske nauke" to  results[2],
            "STEM" to results[3],
            "Tehničke nauke" to  results[4],
            "Umjetnosti" to  results[5]
        ).sortedBy { (_, value) -> value }.reversed()

        var max = 0
        var grana: String = rez[0].first
        for (item in rez) {
            if (item.second > max) {
                max = item.second
                grana = item.first
            }
        }

        val formattedString = rez.joinToString(separator = "\n") { (name, points) ->
            "$name - $points poena"
        }

        Text(
            text = "Grana za tebe je",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = grana,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        SquareCanvas( modifier =  Modifier
            .padding(10.dp)
            .aspectRatio(1f),
            results
        )

        Text(
            text = formattedString,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = {
                navigateToNextScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Probaj Opet",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }

    }
}

@Composable
fun SquareCanvas(modifier: Modifier = Modifier, results: MutableList<Int>) {
    var size by remember { mutableStateOf(0) }

    Box(
        modifier = modifier.onSizeChanged() { size = it.width }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val squareSize = size.toFloat()
            val centerX = squareSize / 2
            val centerY = squareSize / 2

            val numHexagons = 5
            val initialRadius = squareSize / 2
            val radiusStep = initialRadius / numHexagons

            for (i in 0 until numHexagons) {
                val currentRadius = initialRadius - (i * radiusStep)
                val sides = 6
                val angle = (Math.PI * 2 / sides).toFloat()

                val path = Path()

                for (j in 0 until sides) {
                    val x = centerX + (currentRadius * cos(angle * j)).toFloat()
                    val y = centerY + (currentRadius * sin(angle * j)).toFloat()

                    if (j == 0) {
                        path.moveTo(x, y)
                    } else {
                        path.lineTo(x, y)
                    }
                }

                path.close()

                val strokeWidth = if (i == 0) 4.dp.toPx() else 2.dp.toPx()
                val color = if (i == 0) Color.Black else Color.Gray

                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                if (i == numHexagons-1) {
                    //val values = listOf(0.7f, 0.5f, 0.8f, 0.5f, 0.7f, 0.2f) // Example values

                    val values = mutableListOf<Float>()

                    for (item in results) {
                        values.add(item.toFloat() / 12.0f)
                    }

                    drawIrregularHexagon(centerX, centerY, initialRadius, values)
                }
            }
        }
    }
}


fun DrawScope.drawIrregularHexagon(centerX: Float, centerY: Float, radius: Float, values: List<Float>) {
    if (values.size != 6) {
        throw IllegalArgumentException("Values list must contain exactly six values.")
    }

    val sides = 6
    val angle = (Math.PI * 2 / sides).toFloat()

    val path = Path()

    for (i in 0 until sides) {
        val currentRadius = radius * values[i]
        val x = centerX + (currentRadius * cos(angle * i)).toFloat()
        val y = centerY + (currentRadius * sin(angle * i)).toFloat()

        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }

    path.close()

    drawPath(
        path = path,
        color = Color(0x770F61A4), // Half-transparent green color
        style = Fill
    )

    drawPath(
        path = path,
        color = Color(0xFF0F61A4), // Half-transparent green color
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
    )

}





