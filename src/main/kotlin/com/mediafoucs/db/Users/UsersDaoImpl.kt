package com.mediafoucs.db.Users

import com.mediafoucs.db.Users.UsersFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq

class UsersDaoImpl : UsersDao {


    override suspend fun createUser(userId: String, email: String, date: String) {
        val userExist = dbQuery { Users.select{ Users.id eq userId}.count() > 0 }
        if(!userExist){
            dbQuery{
                Users.insert {
                    it[Users.id] = userId
                    it[Users.name] = ""
                    it[Users.email] = email
                    it[Users.date] = date
                    it[Users.phone] = ""
                    it[Users.plan] = ""
                }
            }
        }
    }

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        name = row[Users.name],
        phone = row[Users.phone],
        email = row[Users.email],
        date = row[Users.date],
        plan = row[Users.plan]
    )

    override suspend fun viewAllUsers(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun updateUser(id: String, name: String, phone: String, email: String, plan: String) {
        dbQuery {
            println(id)
            println(name)
            println(phone)
            println(email)
            println(plan)
            Users.update({ Users.id eq id}){
                it[Users.name] = name
                it[Users.phone] = phone
                it[Users.email] = email
                it[Users.plan] = plan
            }
        }
    }

    override suspend fun removeAUser(id: String): Boolean {
      return  dbQuery {
            Users.deleteWhere{ Users.id eq id }
        } > 0
    }

    override suspend fun getAllUnRegUsers(): List<User> {
        return dbQuery {
            Users.select(Users.name eq "").map(::resultRowToUser)
        }
    }

    override suspend fun getAllRegUsers(): List<User> {
        return dbQuery {
            Users.select( Users.name neq "" ).map(::resultRowToUser)
        }
    }

    override suspend fun getMyDetails(id: String): User {
        return dbQuery {
            Users.select( Users.id eq id ).map(::resultRowToUser).first()
        }
    }
}