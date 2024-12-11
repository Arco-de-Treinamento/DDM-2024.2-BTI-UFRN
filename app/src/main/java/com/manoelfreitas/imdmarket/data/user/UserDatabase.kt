package com.manoelfreitas.imdmarket.data.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manoelfreitas.imdmarket.user.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}