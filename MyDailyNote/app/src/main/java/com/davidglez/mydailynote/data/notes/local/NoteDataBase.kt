package com.davidglez.mydailynote.data.notes.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidglez.mydailynote.data.notes.model.NoteEntity

/**
 * Created by davidgonzalez on 31/03/23
 */
@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}