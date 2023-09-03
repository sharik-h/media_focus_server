package com.mediafoucs.db.Expense

import com.mediafoucs.db.Payments.PaymentFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExpenseDaoImpl : ExpenseDao {

    override suspend fun addExp(id: Int, desc: String, amount: Int, date: String, ) {
        dbQuery {
            Expenses.insert {
                it[Expenses.id] = id
                it[Expenses.desc] = desc
                it[Expenses.amount] = amount
                it[Expenses.date] = date
            }
        }
    }

    private fun resultRowToPayment(row: ResultRow) = Expense(
        id = row[Expenses.id],
        desc = row[Expenses.desc],
        amount = row[Expenses.amount],
        date = row[Expenses.date],
    )

    override suspend fun viewMyExp(): List<Expense> = dbQuery {
        Expenses.selectAll().map(::resultRowToPayment)
    }


    override suspend fun deleteExp(id: Int) {
        dbQuery {
            Expenses.deleteWhere { Expenses.id eq id }
        }
    }
}