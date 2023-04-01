package com.davidglez.mydailynote.data.notes

import androidx.room.*
import com.davidglez.mydailynote.data.notes.utils.Constants.NOTE_TABLE_NAME

/**
 * Created by davidgonzalez on 31/03/23
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM $NOTE_TABLE_NAME")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(vararg noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)
}