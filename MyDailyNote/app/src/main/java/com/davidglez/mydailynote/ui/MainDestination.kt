package com.davidglez.mydailynote.ui

/**
 * Created by davidgonzalez on 02/04/23
 */
sealed class MainDestination(val route: String) {
    object NoteList: MainDestination(route = "NoteList")
    object CreateEditNote: MainDestination(route = "CreateEditNote")
}
