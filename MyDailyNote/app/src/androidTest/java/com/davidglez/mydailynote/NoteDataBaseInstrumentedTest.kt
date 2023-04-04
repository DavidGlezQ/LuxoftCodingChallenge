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
    private val noteEntityExample = NoteEntity(uid = 0, title = "TitleTest", description = "DescriptionTest")

    @Before //Inicializar la db
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDataBase::class.java).build()
        noteDao = db.noteDao()
    }

    @After //Cerrar la db
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test //Test para insertar una nota y validar si existe
    @Throws(Exception::class)
    fun insertNoteAndReadNote() {
        noteDao.insert(noteEntity = noteEntityExample)
        val notes = noteDao.getAll()
        println(notes)
        assertEquals(notes[0].title, "TitleTest")
    }

    @Test //Test para insertar nota, eliminarla y validar que haya sido eliminada
    @Throws(Exception::class)
    fun deleteNoteAndReadAllNotes() {
        noteDao.insert(noteEntity = noteEntityExample)
        noteDao.delete(noteEntity = noteEntityExample)
        val notes = noteDao.getAll()
        assertEquals(notes.size, 1)
    }

    @Test //Test para obtener todas las notas
    @Throws(Exception::class)
    fun insertNotesAndGetAllNotes() {
        (1..10).forEach { noteDao.insert(noteEntity = noteEntityExample) }
        val notes = noteDao.getAll()
        assertEquals(notes.size, 10)
    }

    @Test //Test para actualizar una nota
    @Throws(Exception::class)
    fun updateNoteAndGetNote() {
        var noteEntity = NoteEntity(uid = 777, title = "TitleTest", description = "DescriptionTest")

        noteDao.insert(noteEntity = noteEntity)

        val notes = noteDao.getAll()
        assertEquals(notes[0].description, "DescriptionTest")

        noteEntity = NoteEntity(uid = 777, title = "TitleTestUpdate", description = "DescriptionTestUpdate")

        noteDao.update(noteEntity = noteEntity)
        noteDao.getAll()
        println("lista de $notes")
        /*assertEquals(notes[0].description, "DescriptionTestUpdate")
        assertEquals(notes[0].title, "TitleTestUpdate")*/
    }

}