package com.mediafoucs.db.Users

import com.mediafoucs.db.Users.UsersFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UsersDaoImpl : UsersDao {


    override suspend fun createUser(name: String, phone: String, email: String, date: String) {
        dbQuery{
            Users.insert {
                it[Users.name] = name
                it[Users.phone] = phone
                it[Users.email] = email
                it[Users.date] = date
            }
        }
    }

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
        phone = row[Users.phone],
        email = row[Users.email],
        date = row[Users.date]
    )

    override suspend fun viewAllUsers(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun updateUser(id: Int, name: String, phone: String, email: String, date: String) {
        dbQuery {
            Users.update({ Users.id eq id}){
                it[Users.name] = name
                it[Users.phone] = phone
                it[Users.email] = email
                it[Users.date] = date
            }
        }
    }

    override suspend fun removeAUser(id: Int): Boolean {
      return  dbQuery {
            Users.deleteWhere{ Users.id eq id }
        } > 0
    }
}