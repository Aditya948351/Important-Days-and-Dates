package com.importantdays.domain.usecase

import com.importantdays.domain.repository.DayRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: DayRepository
) {
    suspend operator fun invoke(id: Int) {
        val day = repository.getDayById(id)
        if (day != null) {
            repository.updateDay(day.copy(isFavorite = !day.isFavorite))
        }
    }
}
