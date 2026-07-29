package com.importantdays.domain.usecase

import com.importantdays.domain.model.Day
import com.importantdays.domain.repository.DayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDaysUseCase @Inject constructor(
    private val repository: DayRepository
) {
    operator fun invoke(query: String): Flow<List<Day>> {
        return repository.searchDays(query)
    }
}
