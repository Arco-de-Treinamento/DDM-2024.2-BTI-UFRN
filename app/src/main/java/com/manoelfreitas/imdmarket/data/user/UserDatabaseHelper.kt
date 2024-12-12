package com.manoelfreitas.imdmarket.data.user

import android.content.Context
import androidx.room.Room

class UserDatabaseHelper {
    private var userDB: UserDatabase? = null

    fun getUserDatabase(context: Context): UserDatabase? {
        if(userDB == null) {
            userDB = Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            ).allowMainThreadQueries().build()
        }

        //Por padrao o Room Bloqueia o acesso ao banco de dados na Main Thread.
        //A implementação correta iria utilizar o coroutines para isso.

        return userDB!!
    }
}