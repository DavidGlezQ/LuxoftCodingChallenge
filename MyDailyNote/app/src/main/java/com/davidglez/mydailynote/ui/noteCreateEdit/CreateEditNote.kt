package com.davidglez.mydailynote.ui.noteCreateEdit

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.davidglez.mydailynote.ui.components.TfCustom
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme
import com.davidglez.mydailynote.R
import com.davidglez.mydailynote.domain.notes.model.Note
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.components.CounterMaxLength
import com.davidglez.mydailynote.ui.main.NoteEvent
import com.davidglez.mydailynote.ui.noteList.NoteListInteractor


/**
 * Created by davidgonzalez on 01/04/23
 */

@Composable
fun CreateEditNote(onEvent: (NoteEvent) -> Unit,
                   noteListInteractor: NoteListInteractor,
                   onNavigate: (MainDestination) -> Unit) {

    ConstraintLayout {
        val topGuideline = createGuidelineFromTop(16.dp)

        val (textFieldNoteName, textFieldNoteDescription,
            counterNoteDescription ,btnSaveNote) = createRefs()

        var noteNameValue by remember { mutableStateOf("") }
        var notesValue by remember { mutableStateOf("") }

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

        //Button
        Button(
            onClick = {
                onEvent(NoteEvent.AddNote(note = Note(
                    id = 0,
                    title = noteNameValue,
                    description = notesValue))) },
            modifier = Modifier.constrainAs(btnSaveNote) {
                top.linkTo(textFieldNoteDescription.bottom)
            }
            .padding(all = 16.dp)) {
            Text(text = "Save Note")
            Icon(
                Icons.Filled.Done,
                contentDescription = "Note Save",
                modifier = Modifier.size(ButtonDefaults.IconSize))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    MyDailyNoteTheme {
        CreateEditNote(onEvent = {},
            noteListInteractor = NoteListInteractor(),
            onNavigate = {})
    }
}