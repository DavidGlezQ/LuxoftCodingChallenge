package com.davidglez.mydailynote.ui.main.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.main.interactor.NoteListInteractor
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

/**
 * Created by davidgonzalez on 01/04/23
 */

@Composable
fun NoteList(onNavigate: (MainDestination) -> Unit, noteListInteractor: NoteListInteractor, onSelectedNote: (Note) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "New Note")},
                onClick = { onNavigate(MainDestination.CreateEditNote)}, icon = {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add"
                )
            })
        }
    ) {
        com.davidglez.mydailynote.ui.components.NoteList(
            modifier = Modifier.padding(it),
            notes = noteListInteractor.notes,
            onSelectedNote = onSelectedNote)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListScreen() {
    MyDailyNoteTheme {
        NoteList(onNavigate = {}, noteListInteractor = NoteListInteractor(), onSelectedNote = {})
    }
}