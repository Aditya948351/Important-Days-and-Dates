package com.importantdays.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantdays.data.entity.NoteEntity
import com.importantdays.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent: State<String> = _noteContent

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteRepository.getNoteById(noteId).collectLatest { note ->
                        note?.let {
                            currentNoteId = it.id
                            _noteTitle.value = it.title
                            _noteContent.value = it.content
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = event.value
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = event.value
            }
            is AddEditNoteEvent.SaveNote -> {
                if (_noteTitle.value.isNotBlank() || _noteContent.value.isNotBlank()) {
                    viewModelScope.launch {
                        noteRepository.insertNote(
                            NoteEntity(
                                id = currentNoteId ?: 0,
                                title = _noteTitle.value,
                                content = _noteContent.value,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    }
                }
            }
        }
    }
}

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
