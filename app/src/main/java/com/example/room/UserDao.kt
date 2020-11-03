package com.example.room

import androidx.annotation.RequiresPermission
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getUser() : List<User>
}