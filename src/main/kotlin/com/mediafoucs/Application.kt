package com.mediafoucs

import com.auth0.jwk.JwkProviderBuilder
import com.mediafoucs.data.remote.dto.OneSignalServicesImpl
import com.mediafoucs.db.Payments.PaymentFactory
import com.mediafoucs.db.Users.UsersFactory
import com.mediafoucs.di.mainModule
import com.mediafoucs.plugins.configureMonitoring
import com.mediafoucs.plugins.configureRouting
import com.mediafoucs.plugins.configureSecurity
import com.mediafoucs.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import java.util.concurrent.TimeUnit
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun validateCreds(credentials: JWTCredential): JWTPrincipal? {
    val COM_AUTH0_DOMAIN = "dev-1gflpddtglxa60c5.us.auth0.com"
    val containsAudience = credentials.payload.audience.contains("https://dev-1gflpddtglxa60c5.us.auth0.com/api/v2/")

    println(credentials)
    if (containsAudience){
        return JWTPrincipal(credentials.payload)
    }

    return null
}


fun Application.module() {

    val domain = "dev-1gflpddtglxa60c5.us.auth0.com"


    val jwkProvider = JwkProviderBuilder("https://dev-1gflpddtglxa60c5.us.auth0.com/")
        .cached(10,128,TimeUnit.HOURS)
        .rateLimited(10,1,TimeUnit.MINUTES)
        .build()

    install(Authentication){
        jwt("auth0"){
            verifier(jwkProvider,"https://dev-1gflpddtglxa60c5.us.auth0.com/")
            validate { credentials -> validateCreds(credentials) }
        }
    }

    install(CORS){
        anyHost()
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowHeader("Authorization")
        allowCredentials = true
        allowNonSimpleContentTypes = true
    }




    PaymentFactory.init()
    UsersFactory.init()

    install(Koin){
        modules(mainModule)
    }
    configureSecurity()
    configureMonitoring()
    configureSerialization()
//    val client =
    val apiKey = "ZjNmNjljNjEtMTdlMC00MDJiLTgxMGMtZDRhMjIyMWYwNWZk"
    val onesignal = OneSignalServicesImpl(apiKey)
    configureRouting(onesignal)
}
