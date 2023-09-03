package com.mediafoucs.plugins

import com.mediafoucs.data.remote.dto.OneSignalServices
import com.mediafoucs.db.Expense.ExpenseDaoImpl
import com.mediafoucs.db.Payments.PaymentDaoImpl
import com.mediafoucs.db.Users.UsersDaoImpl
import com.mediafoucs.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting(oneSignalServices: OneSignalServices) {
    val paymentDao by inject<PaymentDaoImpl>()
    val userDao by inject<UsersDaoImpl>()
    val expenseDao by inject<ExpenseDaoImpl>()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        sendOnesignalNotification(oneSignalServices)
        addNewPayment(paymentDao)
        getAllPayment(paymentDao)
        savePayment(paymentDao)
        getAPayment(paymentDao)
        addNewUser(userDao)
        getAllUsers(userDao)
        updateAUser(userDao)
        removeAUser(userDao)
        getAllUnRegUser(userDao)
        getAllRegUser(userDao)
        getMyDetails(userDao)
        addNewExp(expenseDao)
        getMyExp(expenseDao)
        deleteExp(expenseDao)
    }
}
