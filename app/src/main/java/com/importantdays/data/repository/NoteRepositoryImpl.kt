package com.importantdays.data.repository

import com.importantdays.data.dao.NoteDao
import com.importantdays.data.entity.NoteEntity
import com.importantdays.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    override fun getNoteById(noteId: Int): Flow<NoteEntity?> = noteDao.getNoteById(noteId)

    override suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)

    override suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
}
