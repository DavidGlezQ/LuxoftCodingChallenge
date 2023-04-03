package com.davidglez.mydailynote.ui.main.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.davidglez.mydailynote.R
import com.davidglez.mydailynote.data.notes.model.utils.Constants
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.components.CounterMaxLength
import com.davidglez.mydailynote.ui.components.TfCustom
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

        var noteNameValue by remember { mutableStateOf( selectedNote.title) }
        var notesValue by remember { mutableStateOf(selectedNote.description) }

        //Validadion para saber si va a la lista de notas y devolver la bandera a su estado
        if (noteListInteractor.navToHome) {
            onNavigate(MainDestination.NoteList)
            onEvent(NoteEvent.NotNavToHome)
        }

        //TextField Note Name
        TfCustom(paddingTop = dimensionResource(id = R.dimen.common_padding_nano),
            labelRes = R.string.app_name,
            iconRes = R.drawable.ic_note_name,
            maxLength = integerResource(id = R.integer.name_max_length),
            isRequired = true,
            keyBoardOption = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            modifier = Modifier
                .constrainAs(textFieldNoteName) {
                    top.linkTo(topGuideline)
                }
                .padding(start = 16.dp, end = 16.dp)
        ) { noteNameValue = it }

        //TextField Note Description
        TfCustom(
            labelRes = R.string.app_name,
            iconRes = R.drawable.ic_note_description,
            isSingleLine = false,
            maxLength = integerResource(id = R.integer.notes_max_length),
            keyBoardOption = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .constrainAs(textFieldNoteDescription) {
                    top.linkTo(textFieldNoteName.bottom, margin = 8.dp)
                }
                .padding(start = 16.dp, end = 16.dp)
        ) { notesValue = it }

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
            modifier = Modifier.constrainAs(btnSaveNote) {
                top.linkTo(textFieldNoteDescription.bottom)
            }
                .padding(all = 16.dp)) {
            Text(text = "Update Note")
            Icon(
                Icons.Filled.Done,
                contentDescription = "Note Update",
                modifier = Modifier.size(ButtonDefaults.IconSize))
        }

        //Button Delete
        Button(
            onClick = {
                onEvent(NoteEvent.DeleteNote(note = Note(
                    id = selectedNote.id,
                    title = selectedNote.title,
                    description = selectedNote.description))) },
            modifier = Modifier.constrainAs(btnDeleteNote) {
                top.linkTo(btnSaveNote.bottom)
            }
                .padding(all = 16.dp)) {
            Text(text = "Delete Note")
            Icon(
                Icons.Filled.Done,
                contentDescription = "Note Delete",
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