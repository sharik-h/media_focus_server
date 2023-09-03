package com.mediafoucs.routes

import com.mediafoucs.db.Expense.ExpenseDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addNewExp(dao: ExpenseDaoImpl) {
    post("/addNewExp"){
        call.respond(HttpStatusCode.OK)
        val id = call.parameters["id"] ?: ""
        val desc = call.parameters["desc"] ?: ""
        val amount = call.parameters["amount"] ?: ""
        val date = call.parameters["date"] ?: ""
        dao.addExp(id = id.toInt(), desc = desc, amount = amount.toInt(), date = date)
    }
}

fun Route.getMyExp(dao: ExpenseDaoImpl) {
    get("getAllExpense"){
        call.respond(HttpStatusCode.OK, dao.viewMyExp())
    }
}

fun Route.deleteExp(dao: ExpenseDaoImpl) {
    delete {
        val id = call.parameters["id"]?.toInt() ?: 0
        call.respond(HttpStatusCode.OK)
        dao.deleteExp(id)
    }
}