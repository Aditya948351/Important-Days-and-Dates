package com.importantdays.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(category: String, modifier: Modifier = Modifier) {
    val backgroundColor = getCategoryColor(category)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor.copy(alpha = 0.2f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = backgroundColor
        )
    }
}

fun getCategoryColor(category: String): Color {
    return when (category) {
        "Global" -> Color(0xFF1E88E5)
        "Cultural" -> Color(0xFF8E24AA)
        "International" -> Color(0xFF00897B)
        "Environmental" -> Color(0xFF43A047)
        "National" -> Color(0xFFE53935)
        "Religious" -> Color(0xFFFFB300)
        else -> Color(0xFF757575)
    }
}
