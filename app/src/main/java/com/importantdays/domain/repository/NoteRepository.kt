package com.importantdays.domain.repository

import com.importantdays.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getNoteById(noteId: Int): Flow<NoteEntity?>
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}
