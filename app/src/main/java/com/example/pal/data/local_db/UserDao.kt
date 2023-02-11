package com.example.pal.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pal.data.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users_table WHERE email = :email")
    fun getUser(id: String): LiveData<User>

    @Update
    suspend fun updateUser(user: User)

}