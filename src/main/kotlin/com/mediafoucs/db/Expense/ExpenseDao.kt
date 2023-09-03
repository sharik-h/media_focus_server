package com.mediafoucs.db.Expense

interface ExpenseDao {
    suspend fun addExp(id: Int, desc: String, amount: Int, date: String)
    suspend fun viewMyExp(): List<Expense>
    suspend fun deleteExp(id: Int)
}