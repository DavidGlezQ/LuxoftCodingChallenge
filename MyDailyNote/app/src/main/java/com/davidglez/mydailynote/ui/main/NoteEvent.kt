package com.davidglez.mydailynote.ui.main

import com.davidglez.mydailynote.domain.notes.model.Note

/**
 * Created by davidgonzalez on 02/04/23
 */
sealed class NoteEvent {
    data class AddNote(val note: Note): NoteEvent()
}
