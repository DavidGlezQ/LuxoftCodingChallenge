package com.davidglez.mydailynote.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

/**
 * Created by davidgonzalez on 02/04/23
 */
@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onSelectedNote: (note: Note) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 175.dp),
        modifier = modifier.fillMaxSize()
    ) {
       items(items = notes) {note ->
           NoteCard(
               modifier = Modifier
                   .padding(10.dp)
                   .height(100.dp)
                   .width(150.dp),
               note = note, onSelectedNote = onSelectedNote)
       }
    }
}

@Preview
@Composable
fun PreviewNoteList() {
    MyDailyNoteTheme {
        val notes = listOf(
            Note(id = 0, title = "Title Test", description = "Description Test"),
            Note(id = 0, title = "Title Test", description = "Description Test"),
            Note(id = 0, title = "Title Test", description = "Description Test"),
            Note(id = 0, title = "Title Test", description = "Description Test"))
        NoteList(notes = notes, onSelectedNote = {})
    }
}