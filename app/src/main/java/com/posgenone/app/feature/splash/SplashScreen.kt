package com.posgenone.app.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import com.posgenone.app.R


@Composable
fun SplashScreen(onAnimationFinished: () -> Unit) {
    val backgroundColor = Color(0xFF222125)

    val iconOffsetX = remember { Animatable(0f) }
    val textOffsetX = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(1f) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    LaunchedEffect(Unit) {
        val pxWidth = with(density) { screenWidth.toPx() }

        iconOffsetX.snapTo(pxWidth)
        textOffsetX.snapTo(pxWidth)

        iconOffsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
        )

        delay(100)

        textOffsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
        )

        delay(700)

        onAnimationFinished()
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_pos_genone),
                contentDescription = "Logo Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(72.dp)
                    .offset { IntOffset(iconOffsetX.value.roundToInt(), 0) }
            )

            Spacer(modifier = Modifier.width(12.dp))

            LogoText(
                offsetX = textOffsetX.value,
                alpha = textAlpha.value
            )
        }
    }
}

@Composable
fun LogoText(
    offsetX: Float = 0f,
    alpha: Float = 1f
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .alpha(alpha)
    ) {
        Text(
            text = "POS",
            color = Color(0xFFC9AA7F),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "GenOne",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



