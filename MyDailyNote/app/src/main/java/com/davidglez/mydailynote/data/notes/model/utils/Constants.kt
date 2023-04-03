package com.davidglez.mydailynote.data.notes.model.utils

import com.davidglez.mydailynote.domain.notes.model.Note

/**
 * Created by davidgonzalez on 31/03/23
 */
object Constants {
    const val NOTE_TABLE_NAME = "notesDB"
    const val NOTE_DATABASE = "note_DB"

    val notes = listOf(
        Note(id = 0, title = "Title Test", description = "Description Test"),
        Note(id = 0, title = "Title Test", description = "Description Test"),
        Note(id = 0, title = "Title Test", description = "Description Test"),
        Note(id = 0, title = "Title Test", description = "Description Test")
    )
}