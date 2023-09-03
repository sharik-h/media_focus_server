package com.mediafoucs.db.Users

interface UsersDao {
    suspend fun createUser(userId: String, email: String, date: String)
    suspend fun viewAllUsers(): List<User>
    suspend fun updateUser(id: String, name:String, phone: String, email: String, plan: String)
    suspend fun removeAUser(id: String): Boolean
    suspend fun getAllUnRegUsers(): List<User>
    suspend fun getAllRegUsers(): List<User>
    suspend fun getMyDetails(id: String): User
}