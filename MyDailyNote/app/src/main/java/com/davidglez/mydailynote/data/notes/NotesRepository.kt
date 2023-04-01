package com.davidglez.mydailynote.data.notes

import com.davidglez.mydailynote.data.notes.local.NoteDao
import com.davidglez.mydailynote.data.notes.model.NoteEntity
import javax.inject.Inject

/**
 * Created by davidgonzalez on 31/03/23
 */
class NotesRepository @Inject constructor(private val noteDao: NoteDao){

    fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAll()
    }

    fun insertNote(noteEntity: NoteEntity) {
        noteDao.insert(noteEntity = noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) {
        noteDao.update(noteEntity = noteEntity)
    }

    fun deleteNote(noteEntity: NoteEntity) {
        noteDao.delete(noteEntity = noteEntity)
    }
}