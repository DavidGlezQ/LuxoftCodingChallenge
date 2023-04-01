package com.davidglez.mydailynote.domain.notes.model

import com.davidglez.mydailynote.data.notes.model.NoteEntity

/**
 * Created by davidgonzalez on 31/03/23
 */
data class Note(val id: Int, val title: String, val description: String)

fun NoteEntity.toNote(): Note = Note(id = uid, title = title, description = description)
fun Note.toNoteEntity(): NoteEntity = NoteEntity(uid = id, title = title, description = description)
