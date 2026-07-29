package com.importantdays.domain.usecase

import com.importantdays.domain.model.Day
import com.importantdays.domain.repository.DayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDaysUseCase @Inject constructor(
    private val repository: DayRepository
) {
    operator fun invoke(): Flow<List<Day>> {
        return repository.getAllDays()
    }

    fun byMonth(month: Int): Flow<List<Day>> {
        return repository.getDaysByMonth(month)
    }

    fun favorites(): Flow<List<Day>> {
        return repository.getFavoriteDays()
    }
}
