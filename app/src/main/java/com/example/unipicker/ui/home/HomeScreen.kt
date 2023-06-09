package com.example.unipicker.ui.home

import androidx.compose.runtime.Composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.unipicker.R


@Composable
fun HomeScreen(
    onNextButtonClicked: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val verticalPaddingTop = (screenHeight * 0.05f)
    val verticalPaddingBottom = (screenHeight * 0.20f)
    val horizontalPadding = (screenWidth * 0.15f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = verticalPaddingTop,
                bottom = verticalPaddingBottom,
                start = horizontalPadding,
                end = horizontalPadding
    ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Koji fakultet je ZA TEBE??",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val imageModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = CircleShape)
        val image: Painter = painterResource(id = R.drawable.naslovna) // Replace with your image resource

        Image(
            painter = image,
            contentDescription = "Image",
            modifier = imageModifier,
        )

        Spacer(modifier = Modifier.height(verticalPaddingBottom))

        AnimatedButton(onNextButtonClicked)
    }
}

@Composable
fun AnimatedButton(onNextButtonClicked: () -> Unit) {
    var isButtonUp by remember { mutableStateOf(false) }

    val animatedValue by animateDpAsState(
        targetValue = if (isButtonUp) 0.dp else 16.dp,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) {
        isButtonUp = true
    }

    Button(
        onClick = {
            onNextButtonClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .offset(y = animatedValue)
    ) {
        Text(
            text = "Započni kviz",
            modifier = Modifier.padding(2.dp),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
