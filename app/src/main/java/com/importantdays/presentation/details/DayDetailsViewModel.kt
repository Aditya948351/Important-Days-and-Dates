package com.importantdays.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantdays.domain.model.Day
import com.importantdays.domain.usecase.GetDayDetailsUseCase
import com.importantdays.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayDetailsViewModel @Inject constructor(
    private val getDayDetailsUseCase: GetDayDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dayId: Int = checkNotNull(savedStateHandle["dayId"])

    private val _day = MutableStateFlow<Day?>(null)
    val day: StateFlow<Day?> = _day.asStateFlow()

    init {
        getDayDetails()
    }

    private fun getDayDetails() {
        viewModelScope.launch {
            _day.value = getDayDetailsUseCase(dayId)
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            toggleFavoriteUseCase(dayId)

            getDayDetails()
        }
    }
}
