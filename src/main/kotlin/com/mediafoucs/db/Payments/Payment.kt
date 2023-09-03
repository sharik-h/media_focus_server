package com.mediafoucs.db.Payments

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Payment(
    val id: Int,
    val userId: String,
    val amount: Int,
    val billDate: String,
    val date: String,
    val status: Boolean,
    val payerNo: String,
    val payerName: String,
    val transactionId: String
)

object Payments: Table() {
    val id = integer("id").autoIncrement()
    val userId = varchar("userId", 128)
    val amount = integer("amount")
    val billDate = varchar("billDate", 128)
    val date = varchar("date", 128)
    val status = bool("status")
    val payerNo = varchar("payerNo", 128)
    val payerName = varchar("payerName", 128)
    val transactionId = varchar("transactionId", 128)

    override val primaryKey = PrimaryKey(id)
}