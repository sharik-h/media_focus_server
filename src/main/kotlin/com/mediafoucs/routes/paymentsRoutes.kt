package com.mediafoucs.routes

import com.mediafoucs.db.Payments.PaymentDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addNewPayment(dao: PaymentDaoImpl) {
    get("/addPay"){
        val userId = call.parameters["userId"]
        val amount = call.parameters["amount"]?.toInt()
        val date = call.parameters["date"]
        dao.addPayments(userId!!, amount!!, date!!)
    }
}

fun Route.getAllPayment(dao: PaymentDaoImpl) {
    get("/getAllPayment") {
        call.respond(HttpStatusCode.OK, dao.viewAllPayments())
    }
}

fun Route.getAPayment(dao: PaymentDaoImpl) {
    get("getAPayment") {
        val id = call.parameters["id"]?.toInt() ?: return@get
        call.respond(dao.viewAPayment(id) ?: "empty")
    }
}