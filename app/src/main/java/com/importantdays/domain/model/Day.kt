package com.importantdays.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Day(
    val id: Int,
    val day: Int,
    val month: Int,
    val title: String,
    val category: String,
    val description: String = "",
    val isFavorite: Boolean = false,
    val imageUrl: String? = null
)
