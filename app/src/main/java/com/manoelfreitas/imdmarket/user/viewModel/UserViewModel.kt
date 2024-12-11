package com.manoelfreitas.imdmarket.user.viewModel

import android.content.Context
import androidx.room.Room
import com.manoelfreitas.imdmarket.data.user.UserDao
import com.manoelfreitas.imdmarket.data.user.UserDatabase
import com.manoelfreitas.imdmarket.data.user.UserDatabaseHelper
import com.manoelfreitas.imdmarket.user.model.User
//
class UserViewModel (context: Context): UserDao{
    private val _userDatabase = UserDatabaseHelper().getUserDatabase(context)

    override fun getAll(): List<User> {
        val users: List<User> = _userDatabase!!.userDao().getAll()

        return users
    }

    override fun findByName(first: String): User {
        val user: User = _userDatabase!!.userDao().findByName(first)

        return user
    }

    override fun insertUser(user: User){

            _userDatabase!!.userDao().insertUser(user)

    }

    fun createUser(username: String, password: String): Boolean {
        val _user: User = User(uid = _userDatabase!!.userDao().getAll().size + 1,
                            userName = username,
                            password = password)

        return try {
            _userDatabase!!.userDao().insertUser(_user)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun deleteUser(user: User) {
            _userDatabase!!.userDao().deleteUser(user)

    }

    fun userLogin(username: String, password: String): Boolean {
        val user: User = _userDatabase!!.userDao().findByName(username)
        return user.password == password
    }
}