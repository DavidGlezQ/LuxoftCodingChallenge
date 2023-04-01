package com.davidglez.mydailynote

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.davidglez.mydailynote.data.notes.local.NoteDao
import com.davidglez.mydailynote.data.notes.local.NoteDataBase
import com.davidglez.mydailynote.data.notes.model.NoteEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by davidgonzalez on 31/03/23
 */
@RunWith(AndroidJUnit4::class)
class NoteDataBaseInstrumentedTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDataBase::class.java).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeNoteAndReadAllNotes() {
        val noteEntity = NoteEntity(uid = 1, title = "TitleTest", description = "DescriptionTest")

        noteDao.insert(noteEntity = noteEntity)

        val notes = noteDao.getAll()

        noteDao.delete(noteEntity = noteEntity)

        println(notes)
        assertEquals(notes[0].title, "TitleTest")
    }
}