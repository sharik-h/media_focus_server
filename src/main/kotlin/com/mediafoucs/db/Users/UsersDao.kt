package com.mediafoucs.db.Users

interface UsersDao {
    suspend fun createUser(name: String, phone: String, email: String, date: String)
    suspend fun viewAllUsers(): List<User>
    suspend fun updateUser(id: Int, name:String, phone: String, email: String, date: String)
    suspend fun removeAUser(id: Int): Boolean
}