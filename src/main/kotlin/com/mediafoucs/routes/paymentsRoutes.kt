package com.mediafoucs.routes

import com.mediafoucs.db.Payments.PaymentDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addNewPayment(dao: PaymentDaoImpl) {
    get("/addPay"){
        val userId = call.parameters["userId"]
        val amount = call.parameters["amount"]?.toInt()
        val billDate = call.parameters["billDate"]
        val date = call.parameters["date"] ?: ""
        val status = call.parameters["status"]
        val payerNo = call.parameters["payerNo"]
        val payerName = call.parameters["payerName"]
        val transactionId = call.parameters["transactionId"] ?: ""
        dao.addPayments(userId!!, amount!!, billDate!!, date, status!!, payerNo!!, payerName!!, transactionId)
    }
}

fun Route.getAllPayment(dao: PaymentDaoImpl) {
//    authenticate("auth0") {
        get("/getAllPayment") {
//            val principal = call.principal<JWTPrincipal>()
//            val username = principal?.subject
//            println(username)
//            if (username != null) {
                call.respond(HttpStatusCode.OK, dao.viewAllPayments())
//            } else {
//                call.respond(HttpStatusCode.Unauthorized)
//            }
        }
//    }
}

fun Route.savePayment(dao: PaymentDaoImpl){
    get("/savePayment") {
        val id = call.parameters["id"]
        val date = call.parameters["date"]
        val transactionId = call.parameters["transactionId"]
        dao.updatePayment(id = id!!.toInt(), date =  date!!, transactionId =  transactionId!!)
    }
}

fun Route.getAPayment(dao: PaymentDaoImpl) {
    authenticate("auth0") {
        get("getMyPayments") {
            val principal = call.authentication.principal<JWTPrincipal>()
            val userId = principal?.subject ?: ""
            println(userId)
            println(dao.viewMyPayment(userId))
            call.respond(dao.viewMyPayment(userId))
        }
    }
}