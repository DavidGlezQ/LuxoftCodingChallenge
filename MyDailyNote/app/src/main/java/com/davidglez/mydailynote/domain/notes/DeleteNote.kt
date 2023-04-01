package com.davidglez.mydailynote.domain.notes

import com.davidglez.mydailynote.data.notes.NotesRepository
import javax.inject.Inject

/**
 * Created by davidgonzalez on 31/03/23
 */
class DeleteNote @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) {
        notesRepository.deleteNote(noteEntity = note.toNoteEntity())
    }
}