package com.davidglez.mydailynote.ui.noteList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidglez.mydailynote.domain.notes.DeleteNote
import com.davidglez.mydailynote.domain.notes.GetNotes
import com.davidglez.mydailynote.domain.notes.InsertNote
import com.davidglez.mydailynote.domain.notes.UpdateNote
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.main.NoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by davidgonzalez on 02/04/23
 */
@HiltViewModel
class NoteListActivityViewModel @Inject constructor(
    private val getNotes: GetNotes,
    private val addNote: InsertNote,
    private val updateNote: UpdateNote,
    private val deleteNote: DeleteNote) : ViewModel() {

    private val _state: MutableState<NoteListInteractor> = mutableStateOf(NoteListInteractor())
    val state: State<NoteListInteractor> get() = _state

    init {
        collectNotes()
    }

    fun onEvent(noteEvent: NoteEvent) {
        when(noteEvent) {
            is NoteEvent.AddNote -> {
                insertNote(note = noteEvent.note)
            }
            NoteEvent.NavToHome -> {
                setNavToHome()
            }
            NoteEvent.NotNavToHome -> {
                unSetNavToHome()
            }
        }
    }

    private fun collectNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedNotes = getNotes()
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(notes = fetchedNotes)
            }
        }
    }

    private fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            addNote(note)
            collectNotes()
            setNavToHome()
        }
    }

    private fun setNavToHome() { //Navegar a la lista de notas cuando se inserta una nota
        _state.value = _state.value.copy(navToHome = true)
    }

    private fun unSetNavToHome() { //Update NotesList
        _state.value = _state.value.copy(navToHome = false)
    }
}