package com.davidglez.mydailynote.ui.main.interactor

import com.davidglez.mydailynote.domain.notes.model.Note


/**
 * Created by davidgonzalez on 02/04/23
 */
data class NoteListInteractor(
    var notes: List<Note> = emptyList(),
    var navToHome: Boolean = false, // Variable para saber si navegamos a la lista de notas
    var selectedNote: Note = Note()
)