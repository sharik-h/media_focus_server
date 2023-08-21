package com.mediafoucs.db.Users

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val date: String,
)

object Users: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val email = varchar("email", 128)
    val phone = varchar("phone", 10)
    val date = varchar("date", 10)

    override val primaryKey = PrimaryKey(id)
}