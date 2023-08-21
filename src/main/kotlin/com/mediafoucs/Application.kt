package com.mediafoucs

import com.mediafoucs.db.Payments.PaymentFactory
import com.mediafoucs.db.Users.UsersFactory
import com.mediafoucs.di.mainModule
import com.mediafoucs.plugins.configureMonitoring
import com.mediafoucs.plugins.configureRouting
import com.mediafoucs.plugins.configureSecurity
import com.mediafoucs.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    PaymentFactory.init()
    UsersFactory.init()

    install(Koin){
        modules(mainModule)
    }
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
