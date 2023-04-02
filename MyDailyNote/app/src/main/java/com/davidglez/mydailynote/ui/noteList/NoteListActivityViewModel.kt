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
    private val insertNote: InsertNote,
    private val updateNote: UpdateNote,
    private val deleteNote: DeleteNote) : ViewModel() {

    private val _state: MutableState<NoteListInteractor> = mutableStateOf(NoteListInteractor())
    val state: State<NoteListInteractor> get() = _state

    init {
        collectNotes()
    }

    private fun collectNotes() {
        viewModelScope.launch {
            Dispatchers.IO
            val fetchedNotes = getNotes()
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(notes = fetchedNotes)
            }
        }
    }
}