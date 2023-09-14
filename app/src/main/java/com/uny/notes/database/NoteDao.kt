package com.uny.notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uny.notes.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)


    @Delete
    suspend fun delete(note: Note)


    @Query("SELECT * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("UPDATE notes_table set title = :title,note =  :note where id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)
}