package com.example.unipicker.ui.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.unipicker.data.Question


@Composable
fun QuestionScreen(
    navigateToNextScreen: () -> Unit,
    question: Question,
    sendBackResponse : (Int) -> Unit,
    last: Boolean
) {
    // State to hold the selected agreement level
    var agreementLevel by remember { mutableStateOf(3) }

    var agreementText = when (agreementLevel) {
        1 -> "Ne, nikako"
        2 -> "Pomalo, ne puno"
        3 -> "Donekle se slazem"
        4 -> "Slazem se"
        5 -> "Apsolutno Alaha mii"
        else -> "Unknown"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.text,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Slider(
            value = agreementLevel.toFloat(),
            onValueChange = { value -> agreementLevel = value.toInt() },
            valueRange = 1f..5f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = " ${agreementText} ",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            if (last)
                navigateToNextScreen()
            else
            sendBackResponse(agreementLevel)
        }) {

            Text(
                text = "Dalje",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}


