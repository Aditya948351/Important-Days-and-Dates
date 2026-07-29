package com.importantdays.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.importantdays.domain.model.Day

@Entity(tableName = "days")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: Int,
    val month: Int,
    val title: String,
    val category: String,
    val description: String = "",
    val isFavorite: Boolean = false,
    val imageUrl: String? = null
)

fun DayEntity.toDomainModel(): Day {
    return Day(
        id = id,
        day = day,
        month = month,
        title = title,
        category = category,
        description = description,
        isFavorite = isFavorite,
        imageUrl = imageUrl
    )
}

fun Day.toEntity(): DayEntity {
    return DayEntity(
        id = id,
        day = day,
        month = month,
        title = title,
        category = category,
        description = description,
        isFavorite = isFavorite,
        imageUrl = imageUrl
    )
}
