package com.mediafoucs.db.Expense

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Expense(
    val id: Int,
    val desc: String,
    val amount: Int,
    val date: String,
)

object Expenses: Table() {
    val id = integer("id")
    val desc = varchar("desc", 128)
    val amount = integer("amount")
    val date = varchar("date", 128)

    override val primaryKey = PrimaryKey(id)
}