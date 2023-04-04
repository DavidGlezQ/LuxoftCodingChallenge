package com.davidglez.mydailynote

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.davidglez.mydailynote.ui.theme.MyDailyNoteTheme
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Created by davidgonzalez on 03/04/23
 */
@RunWith(AndroidJUnit4::class)
class CreateNoteKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyDailyNoteTheme {
                //(modifier = Modifier.fillMaxSize().background(Color.Black))
            }
        }
    }
}