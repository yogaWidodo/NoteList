package com.uny.notes.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addToDatabase(login: Login)

    @Query("SELECT EXISTS(SELECT* FROM user_login where username=:username)")
    suspend fun is_taken(username: String): Boolean

    @Query("SELECT EXISTS(SELECT* FROM user_login where email=:email)")
    suspend fun getUserEmail(email: String): Boolean


    @Query("UPDATE user_login set password = :newPassword where email = :email")
    suspend fun updatePassword(email: String, newPassword: String)


    @Query("SELECT EXISTS(SELECT*FROM user_login where email=:email AND password=:password)")
    suspend fun login(email: String, password: String): Boolean
}