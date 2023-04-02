package com.davidglez.mydailynote.ui.splash

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
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.davidglez.mydailynote.R
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDailyNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    LottieAnimationView()
                }
            }
        }
    }

}

@Composable
fun LottieAnimationView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.my_daily_note_splash_animation))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}





