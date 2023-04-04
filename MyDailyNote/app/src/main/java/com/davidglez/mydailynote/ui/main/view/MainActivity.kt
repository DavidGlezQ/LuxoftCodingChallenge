package com.davidglez.mydailynote.ui.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.main.NoteEvent
import com.davidglez.mydailynote.ui.main.viewModel.NoteListActivityViewModel
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDailyNoteTheme {
                val noteViewModel: NoteListActivityViewModel = hiltViewModel()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MainDestination.SplashScreen.route) {
                    composable(MainDestination.SplashScreen.route) {
                        Splash()
                        LaunchedEffect(key1 = true) {
                            delay(3500)
                            navController.popBackStack() //limpiar stack
                            navController.navigate(MainDestination.NoteList.route)
                        }
                    }
                    composable(MainDestination.NoteList.route) {
                        NoteList(onNavigate = { screen ->
                            navigate(navHostController = navController, screenDestination = screen)
                        }, noteListInteractor = noteViewModel.state.value, onSelectedNote = {
                            noteViewModel.onEvent(NoteEvent.SelectedNote(note = it))
                            navigate(navHostController = navController, screenDestination = MainDestination.UpdateScreen)
                        })
                    }
                    composable(MainDestination.CreateEditNote.route) {
                        CreateEditNote(onEvent = { event ->
                            noteViewModel.onEvent(noteEvent = event )
                        }, noteListInteractor = noteViewModel.state.value, onNavigate = {
                            navigate(navHostController = navController, screenDestination = it)
                        })
                    }
                    composable(MainDestination.UpdateScreen.route) {
                        UpdateScreenActivity(
                            selectedNote =  noteViewModel.state.value.selectedNote ,
                            onNavigate = { navigate(navHostController = navController, screenDestination = it) },
                            noteListInteractor = noteViewModel.state.value,
                            onEvent = { noteViewModel.onEvent(it) }
                        )
                    }
                }
            }
        }
    }

    private fun navigate(navHostController: NavHostController, screenDestination: MainDestination) {
        navHostController.navigate(screenDestination.route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}





