package com.mediafoucs.plugins

import com.mediafoucs.db.Payments.PaymentDaoImpl
import com.mediafoucs.db.Users.UsersDaoImpl
import com.mediafoucs.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val paymentDao by inject<PaymentDaoImpl>()
    val userDao by inject<UsersDaoImpl>()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        addNewPayment(paymentDao)
        getAllPayment(paymentDao)
        getAPayment(paymentDao)
        addNewUser(userDao)
        getAllUsers(userDao)
        updateAUser(userDao)
        removeAUser(userDao)
    }
}
