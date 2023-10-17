package com.uny.notes.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert
    suspend fun addToDatabase(login: Login)

    @Query("SELECT EXISTS(SELECT* FROM user_login where username=:username)")
    suspend fun is_taken(username: String): Boolean

    @Query("SELECT EXISTS(SELECT*FROM user_login where email=:email AND password=:password)")
    suspend fun login(email: String, password: String):Boolean
}