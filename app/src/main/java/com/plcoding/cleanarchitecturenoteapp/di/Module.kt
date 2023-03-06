package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.data.datasource.AppDatabase
import com.plcoding.cleanarchitecturenoteapp.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.domain.usecase.*
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {
  @Provides
  @Singleton
  fun provideAppDatabase(app:Application): AppDatabase {
      return Room.databaseBuilder(
          app,
          AppDatabase::class.java, "note"
      ).build()
   }
    @Provides
    @Singleton
    fun provideNoteRepository(appDatabase:AppDatabase):NoteRepository{
        return NoteRepositoryImpl(appDatabase.userDao());
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getAllNotes = GetAllNotes(noteRepository),
            insertNote = InsertNote(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            getNoteByID = GetNoteByID(noteRepository),
            updateNotes = UpdateNotes(noteRepository)
        )
    }
}