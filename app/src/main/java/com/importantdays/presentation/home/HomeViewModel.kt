package com.importantdays.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantdays.domain.model.Day
import com.importantdays.domain.usecase.GetDaysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MonthCount(val national: Int = 0, val international: Int = 0)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDaysUseCase: GetDaysUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Day>>(emptyList())
    val favorites: StateFlow<List<Day>> = _favorites.asStateFlow()

    private val _monthDayCounts = MutableStateFlow<Map<Int, MonthCount>>(emptyMap())
    val monthDayCounts: StateFlow<Map<Int, MonthCount>> = _monthDayCounts.asStateFlow()

    init {
        getFavorites()
        getAllDaysCounts()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getDaysUseCase.favorites()
                .catch {  }
                .collect { days ->
                    _favorites.value = days
                }
        }
    }

    private fun getAllDaysCounts() {
        viewModelScope.launch {
            getDaysUseCase()
                .catch {  }
                .collect { days ->
                    val counts = mutableMapOf<Int, MonthCount>()
                    for (i in 1..12) {
                        counts[i] = MonthCount(0, 0)
                    }
                    days.forEach { day ->
                        val currentCount = counts[day.month] ?: MonthCount()
                        if (day.category.contains("National", ignoreCase = true)) {
                            counts[day.month] = currentCount.copy(national = currentCount.national + 1)
                        } else if (day.category.contains("International", ignoreCase = true)) {
                            counts[day.month] = currentCount.copy(international = currentCount.international + 1)
                        }
                    }
                    _monthDayCounts.value = counts
                }
        }
    }
}
