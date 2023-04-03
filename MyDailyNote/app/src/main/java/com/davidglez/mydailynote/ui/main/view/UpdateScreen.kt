package com.davidglez.mydailynote.ui.main.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.davidglez.mydailynote.R
import com.davidglez.mydailynote.data.notes.model.utils.Constants
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.components.CounterMaxLength
import com.davidglez.mydailynote.ui.main.NoteEvent
import com.davidglez.mydailynote.ui.main.interactor.NoteListInteractor
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

/**
 * Created by davidgonzalez on 03/04/23
 */

@Composable
fun UpdateScreenActivity(
    selectedNote: Note,
    onNavigate: (MainDestination) -> Unit,
    noteListInteractor: NoteListInteractor,
    onEvent: (noteEvent: NoteEvent) -> Unit) {
    ConstraintLayout {
        val topGuideline = createGuidelineFromTop(16.dp)

        val (textFieldNoteName, textFieldNoteDescription,
            counterNoteDescription, btnSaveNote, btnDeleteNote) = createRefs()

        var noteNameValue by remember { mutableStateOf(selectedNote.title) }
        var notesValue by remember { mutableStateOf(selectedNote.description) }

        //Validadion para saber si va a la lista de notas y devolver la bandera a su estado
        if (noteListInteractor.navToHome) {
            onNavigate(MainDestination.NoteList)
            onEvent(NoteEvent.NotNavToHome)
        }

        //TextField Note Name
        TextField(
            modifier = Modifier
                .constrainAs(textFieldNoteName) {
                    top.linkTo(topGuideline)
                }
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = noteNameValue,
            onValueChange = {
                noteNameValue = it
            },
            label = { Text(text = stringResource(id = R.string.hint_textField_note_name)) },
            singleLine = true
        )

        //TextField Note Description
        TextField(
            modifier = Modifier
                .constrainAs(textFieldNoteDescription) {
                    top.linkTo(textFieldNoteName.bottom, margin = 16.dp)
                }
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = notesValue,
            onValueChange = {
                notesValue = it
            },
            label = { Text(text = stringResource(id = R.string.hint_textField_note_description)) },
            singleLine = true
        )

        //Counter Note Description
        CounterMaxLength(
            modifier = Modifier
                .constrainAs(counterNoteDescription) {
                    top.linkTo(textFieldNoteDescription.bottom)
                }
                .padding(start = 16.dp, end = 16.dp),
            currentLength = notesValue.length,
            maxLengthRes = R.integer.notes_max_length)

        //Button Save
        Button(
            onClick = {
                onEvent(NoteEvent.UpdateNote(note = Note(
                    id = selectedNote.id,
                    title = noteNameValue,
                    description = notesValue))) },
            modifier = Modifier
                .constrainAs(btnSaveNote) {
                    top.linkTo(textFieldNoteDescription.bottom)
                }
                .padding(top = 16.dp, start = 16.dp)) {
            Text(text = stringResource(id = R.string.btn_updateNote_text))
            Icon(
                Icons.Filled.Done,
                contentDescription = stringResource(id = R.string.btn_updateNote_text),
                modifier = Modifier.size(ButtonDefaults.IconSize))
        }

        //Button Delete
        Button(
            onClick = {
                onEvent(NoteEvent.DeleteNote(note = Note(
                    id = selectedNote.id,
                    title = selectedNote.title,
                    description = selectedNote.description))) },
            modifier = Modifier
                .constrainAs(btnDeleteNote) {
                    top.linkTo(btnSaveNote.bottom)
                }
                .padding(top = 8.dp, start = 16.dp)) {
            Text(text = stringResource(id = R.string.btn_deleteNote_text))
            Icon(
                Icons.Filled.Delete,
                contentDescription = stringResource(id = R.string.btn_deleteNote_text),
                modifier = Modifier.size(ButtonDefaults.IconSize))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUpdateScreen() {
    MyDailyNoteTheme {
        UpdateScreenActivity(selectedNote = Constants.example_note, onEvent = {},
            noteListInteractor = NoteListInteractor(),
            onNavigate = {})
    }
}