package com.davidglez.mydailynote.data.notes

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by davidgonzalez on 31/03/23
 */
@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}