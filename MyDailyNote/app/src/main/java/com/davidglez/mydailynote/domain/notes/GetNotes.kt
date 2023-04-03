package com.davidglez.mydailynote.domain.notes

import com.davidglez.mydailynote.data.notes.NotesRepository
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.domain.notes.model.toNote
import javax.inject.Inject

/**
 * Created by davidgonzalez on 31/03/23
 */
class GetNotes @Inject constructor(private val notesRepository: NotesRepository){
    operator fun invoke(): List<Note> {
        return notesRepository.getAllNotes().map { it.toNote() }
    }
}