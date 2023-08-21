package com.mediafoucs.db.Payments

interface PaymentDao {
    suspend fun addPayments(userId: String, amount: Int, date: String)
    suspend fun viewAPayment(id: Int): Payment?
    suspend fun viewAllPayments(): List<Payment>
}