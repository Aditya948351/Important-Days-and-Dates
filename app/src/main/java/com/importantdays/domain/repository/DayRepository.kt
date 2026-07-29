package com.importantdays.domain.repository

import com.importantdays.domain.model.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    fun getAllDays(): Flow<List<Day>>
    fun getDaysByMonth(month: Int): Flow<List<Day>>
    suspend fun getDayById(id: Int): Day?
    fun searchDays(query: String): Flow<List<Day>>
    fun getFavoriteDays(): Flow<List<Day>>
    suspend fun insertDay(day: Day)
    suspend fun insertDays(days: List<Day>)
    suspend fun updateDay(day: Day)
    suspend fun deleteDay(day: Day)
    suspend fun populateInitialData()
}
