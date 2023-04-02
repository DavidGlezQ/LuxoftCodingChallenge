package com.davidglez.mydailynote.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.davidglez.mydailynote.R
import com.davidglez.mydailynote.ui.MainDestination
import com.davidglez.mydailynote.ui.noteCreateEdit.CreateEditNote
import com.davidglez.mydailynote.ui.noteList.NoteList
import com.davidglez.mydailynote.ui.noteList.NoteListActivityViewModel
import com.davidglez.mydailynote.ui.noteList.NoteListInteractor
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDailyNoteTheme {
                //val noteViewModel: NoteListActivityViewModel = hiltViewModel()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MainDestination.NoteList.route) {
                    composable(MainDestination.NoteList.route) {
                        NoteList(onNavigate = { screen ->
                            navigate(navHostController = navController, screenDestination = screen)
                        })
                    }
                    composable(MainDestination.CreateEditNote.route) {
                        CreateEditNote(noteState = NoteListInteractor())
                    }
                }
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    LottieAnimationView()
                }*/
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

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LottieAnimationView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.my_daily_note_splash_animation))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}*/





