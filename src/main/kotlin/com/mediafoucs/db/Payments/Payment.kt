package com.mediafoucs.db.Payments

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Payment(
    val id: Int,
    val userId: String,
    val amount: Int,
    val date: String
)

object Payments: Table() {
    val id = integer("id").autoIncrement()
    val userId = varchar("userId", 128)
    val amount = integer("amount")
    val date = varchar("date", 128)

    override val primaryKey = PrimaryKey(id)
}