package com.davidglez.mydailynote.domain.notes

import com.davidglez.mydailynote.data.notes.NotesRepository
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.domain.notes.model.toNoteEntity
import javax.inject.Inject

/**
 * Created by davidgonzalez on 31/03/23
 */
class UpdateNote @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) {
        notesRepository.updateNote(noteEntity = note.toNoteEntity())
    }
}