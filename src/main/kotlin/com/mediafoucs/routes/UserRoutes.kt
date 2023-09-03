package com.mediafoucs.routes

import com.mediafoucs.db.Users.UsersDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate

fun Route.addNewUser(dao: UsersDaoImpl) {
    authenticate("auth0") {
        post("/addNewUser") {
            call.respond(HttpStatusCode.OK, "called")
            val principal = call.authentication.principal<JWTPrincipal>()
            println(principal?.subject)
            val userId = principal?.subject
            val email = call.parameters["email"]
            dao.createUser(userId = userId!!, email = email!!, date = LocalDate.now().toString())
        }
    }
}

fun Route.getAllUsers(dao: UsersDaoImpl) {
//    authenticate("auth0") {
        get("/geAllUsers") {
            call.respond(dao.viewAllUsers())
        }
//    }
}

fun Route.updateAUser(dao: UsersDaoImpl) {
//    authenticate("auth0") {
        post("/updateAUser") {
//            val principal = call.authentication.principal<JWTPrincipal>()
//            val id = principal?.payload?.getClaim("email")?.asString() ?: ""
            call.respond(HttpStatusCode.OK)
            val id = call.parameters["userId"] ?: ""
            val name = call.parameters["name"] ?: ""
            val phone = call.parameters["phone"] ?: ""
            val email = call.parameters["email"] ?: ""
            val plan = call.parameters["plan"] ?: ""
            dao.updateUser(id = id, name = name, phone = phone, email = email, plan = plan)
        }
//    }
}

fun Route.removeAUser(dao: UsersDaoImpl){
    authenticate("auth0") {
        get("/deleteAUser") {
            val principal = call.authentication.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("email")?.asString() ?: ""
            dao.removeAUser(id)
        }
    }
}

fun Route.getAllUnRegUser(dao: UsersDaoImpl){
    get("getAllUnRegUsers") {
        call.respond(HttpStatusCode.OK, dao.getAllUnRegUsers())
    }
}

fun Route.getAllRegUser(dao: UsersDaoImpl){
    get("getAllRegUsers"){
        call.respond(HttpStatusCode.OK, dao.getAllRegUsers())
    }
}

fun Route.getMyDetails(dao: UsersDaoImpl){
    authenticate("auth0") {
        get("getMyDetails"){
            val principal = call.authentication.principal<JWTPrincipal>()
            val id = principal?.subject.toString()
            println(dao.getMyDetails(id))
            call.respond(HttpStatusCode.OK, dao.getMyDetails(id))
        }
    }
}