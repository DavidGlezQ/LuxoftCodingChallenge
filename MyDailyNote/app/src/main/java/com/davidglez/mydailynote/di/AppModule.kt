package com.davidglez.mydailynote.di

import android.content.Context
import androidx.room.Room
import com.davidglez.mydailynote.data.notes.local.NoteDao
import com.davidglez.mydailynote.data.notes.local.NoteDataBase
import com.davidglez.mydailynote.data.notes.model.utils.Constants.NOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by davidgonzalez on 31/03/23
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesNotesRoomDatabase(@ApplicationContext app: Context): NoteDataBase =
        Room.databaseBuilder(
            app,
            NoteDataBase::class.java, NOTE_DATABASE
        ).build()

    @Provides
    @Singleton
    fun providesNodesDao(noteDataBase: NoteDataBase): NoteDao = noteDataBase.noteDao()
}