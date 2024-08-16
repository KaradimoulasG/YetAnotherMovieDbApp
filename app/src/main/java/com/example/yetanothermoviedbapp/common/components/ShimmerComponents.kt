package com.example.yetanothermoviedbapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerCard(height: Float, brush: Brush, widthFraction: Float = 1f) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(Color.Gray),
//        backgroundColor = Color.Gray,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(widthFraction)
                .background(brush)
                .padding(12.dp)
                .height(Dp(height)),
        )
    }
}

@Composable
fun ShimmerCardView() {
    Column(modifier = Modifier.fillMaxSize().padding(PaddingValues(horizontal = 16.dp))) {
        (0..12).forEach { _ ->
            Spacer(modifier = Modifier.size(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ShimmerCard(height = 150f, brush = createShimmerAnimation())
            }
        }
    }
}

@Preview
@Composable
fun ShimmerCardPreview() {
    ShimmerShowDetails(brush = createShimmerAnimation())
}

@Composable
fun ShimmerShowDetails(brush: Brush = createShimmerAnimation(), widthFraction: Float = 1f) {
    ShimmerCard(height = 100f, brush = brush, widthFraction = 1f)
    Spacer(modifier = Modifier.size(12.dp))
    (0..10).forEach { _ ->
        ShimmerLine(height = 20f, brush = brush)
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun showTopToast() {

}