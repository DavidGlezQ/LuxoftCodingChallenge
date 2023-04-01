package com.davidglez.mydailynote.data.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidglez.mydailynote.data.notes.model.utils.Constants.NOTE_TABLE_NAME

/**
 * Created by davidgonzalez on 31/03/23
 */
@Entity(tableName = NOTE_TABLE_NAME)
data class NoteEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)