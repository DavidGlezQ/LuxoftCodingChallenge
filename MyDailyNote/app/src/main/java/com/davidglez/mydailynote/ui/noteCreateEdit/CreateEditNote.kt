package com.davidglez.mydailynote.ui.noteCreateEdit

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.davidglez.mydailynote.ui.noteList.NoteList
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

/**
 * Created by davidgonzalez on 01/04/23
 */

@Composable
fun CreateEditNote() {
    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    MyDailyNoteTheme {
        CreateEditNote()
    }
}