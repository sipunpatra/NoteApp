package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.db.Note
import com.example.noteapp.db.NoteDao

class NoteRepository(private  val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

}