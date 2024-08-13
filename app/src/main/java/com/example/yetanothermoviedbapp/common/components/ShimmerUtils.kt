package com.example.yetanothermoviedbapp.common.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun createShimmerAnimation(): Brush {
    val gradient = createGradient()
    val transition = rememberInfiniteTransition(label = "") // animate infinite times
    val translateAnimation = createTranslateAnimation(transition)
    return createBrush(gradient, translateAnimation)
}

@Composable
private fun createBrush(
    gradient: List<Color>,
    translateAnimation: State<Float>,
): Brush {
    return Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value,
        ),
    )
}

@Composable
private fun createTranslateAnimation(transition: InfiniteTransition): State<Float> {
    return transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing,
            ),
        ),
        label = "",
    )
}

private fun createGradient(): List<Color> {
    return listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.9f),
    )
}

@Composable
fun ShimmerLine(height: Float, brush: Brush, widthFraction: Float = 1f) {
    Spacer(
        modifier = Modifier
            .height(Dp(height))
            .background(brush)
            .fillMaxWidth(widthFraction),

        )
}