package com.davidglez.mydailynote.data.notes.local

import androidx.room.*
import com.davidglez.mydailynote.data.notes.model.NoteEntity
import com.davidglez.mydailynote.data.notes.model.utils.Constants.NOTE_TABLE_NAME

/**
 * Created by davidgonzalez on 31/03/23
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM $NOTE_TABLE_NAME")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)
}