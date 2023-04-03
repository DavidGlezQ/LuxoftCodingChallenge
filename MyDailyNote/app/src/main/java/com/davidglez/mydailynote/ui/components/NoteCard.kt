package com.davidglez.mydailynote.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

/**
 * Created by davidgonzalez on 02/04/23
 */
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onSelectedNote: (note: Note) -> Unit
) {
    Card(modifier = modifier.clickable { onSelectedNote(note) }) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = note.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = note.description, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
fun PreviewCard() {
    MyDailyNoteTheme {
        NoteCard(note = Note(id = 0, title = "Title Test", description = "Description Test"), onSelectedNote = {})
    }
}