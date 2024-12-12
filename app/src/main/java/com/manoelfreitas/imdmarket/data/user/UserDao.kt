package com.manoelfreitas.imdmarket.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.manoelfreitas.imdmarket.user.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE user_name LIKE :username LIMIT 1")
    fun findByName(username: String): User

    @Query("UPDATE user SET password = :newpassword WHERE user_name = :username")
    fun changePassword(username: String, newpassword: String)

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}