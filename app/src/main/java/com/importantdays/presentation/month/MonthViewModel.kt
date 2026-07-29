package com.importantdays.presentation.month

import androidx.lifecycle.SavedStateHandle
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

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val getDaysUseCase: GetDaysUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _days = MutableStateFlow<List<Day>>(emptyList())
    val days: StateFlow<List<Day>> = _days.asStateFlow()

    private val month: Int = checkNotNull(savedStateHandle["month"])

    init {
        getDaysByMonth()
    }

    private fun getDaysByMonth() {
        viewModelScope.launch {
            getDaysUseCase.byMonth(month)
                .catch {  }
                .collect { list ->
                    _days.value = list
                }
        }
    }
}
