package com.mediafoucs.db.Payments

import com.mediafoucs.db.Payments.PaymentFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class PaymentDaoImpl : PaymentDao {

    override suspend fun addPayments(userId: String, amount: Int, date: String,) {
        dbQuery {
            Payments.insert {
                it[Payments.userId] = userId
                it[Payments.amount] = amount
                it[Payments.date] = date
            }
        }
    }

    private fun resultRowToPayment(row: ResultRow) = Payment(
        id = row[Payments.id],
        userId = row[Payments.userId],
        amount = row[Payments.amount],
        date = row[Payments.date]
    )

    override suspend fun viewAPayment(id: Int): Payment? = dbQuery {
        Payments.select { Payments.id.eq(id) }.map{resultRowToPayment(it)}.singleOrNull()
    }

    override suspend fun viewAllPayments(): List<Payment>  = dbQuery {
        Payments.selectAll().map(::resultRowToPayment)
    }
}