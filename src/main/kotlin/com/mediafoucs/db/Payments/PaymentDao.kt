package com.mediafoucs.db.Payments

interface PaymentDao {
    suspend fun addPayments(
        userId: String,
        amount: Int,
        billDate: String,
        date: String,
        status: String,
        payerNo: String,
        payerName: String,
        transactionId: String)
    suspend fun viewMyPayment(id: String): List<Payment>
    suspend fun viewAllPayments(): List<Payment>
    suspend fun updatePayment(id: Int, transactionId: String, date: String)
}