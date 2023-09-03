package com.mediafoucs.db.Users

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val date: String,
    val plan: String
)

object Users: Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 128)
    val email = varchar("email", 128)
    val phone = varchar("phone", 10)
    val date = varchar("date", 10)
    val plan = varchar("plan", 128)

    override val primaryKey = PrimaryKey(id)
}