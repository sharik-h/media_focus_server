package com.mediafoucs.db.Payments

import com.mediafoucs.db.Payments.PaymentFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PaymentDaoImpl : PaymentDao {

    override suspend fun addPayments(
        userId: String,
        amount: Int,
        billDate: String,
        date: String,
        status: String,
        payerNo: String,
        payerName: String,
        transactionId: String
    ) {
        dbQuery {
            Payments.insert {
                it[Payments.userId] = userId
                it[Payments.amount] = amount
                it[Payments.billDate] = billDate
                it[Payments.date] = date
                it[Payments.status] = status == "true"
                it[Payments.payerNo] = payerNo
                it[Payments.payerName] = payerName
                it[Payments.transactionId] = transactionId
            }
        }
    }

    private fun resultRowToPayment(row: ResultRow) = Payment(
        id = row[Payments.id],
        userId = row[Payments.userId],
        amount = row[Payments.amount],
        billDate = row[Payments.billDate],
        date = row[Payments.date],
        status = row[Payments.status],
        payerNo = row[Payments.payerNo],
        payerName = row[Payments.payerName],
        transactionId= row[Payments.transactionId]
    )

    override suspend fun viewMyPayment(id: String): List<Payment> = dbQuery {
        Payments.select { Payments.userId.eq(id) }.map(::resultRowToPayment)
    }

    override suspend fun updatePayment(id: Int, transactionId: String, date: String){
        dbQuery {
            Payments.update ({ Payments.id.eq(id) }){
                it[Payments.date] = date
                it[Payments.transactionId] = transactionId
                it[Payments.status] = true
            }
        }
    }

    override suspend fun viewAllPayments(): List<Payment>  = dbQuery {
        Payments.selectAll().map(::resultRowToPayment)
    }
}