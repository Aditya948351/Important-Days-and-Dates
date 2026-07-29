package com.importantdays.domain.usecase

import com.importantdays.domain.model.Day
import com.importantdays.domain.repository.DayRepository
import javax.inject.Inject

class GetDayDetailsUseCase @Inject constructor(
    private val repository: DayRepository
) {
    suspend operator fun invoke(id: Int): Day? {
        return repository.getDayById(id)
    }
}
