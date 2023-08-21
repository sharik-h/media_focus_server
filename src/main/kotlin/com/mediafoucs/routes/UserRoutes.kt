package com.mediafoucs.routes

import com.mediafoucs.db.Users.UsersDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.addNewUser(dao: UsersDaoImpl) {
    post("/addNewUser") {
        call.respond(HttpStatusCode.OK, "called")
        val name = call.parameters["name"]
        val phone = call.parameters["phone"]
        val email = call.parameters["email"]
        val date = call.parameters["date"]
        dao.createUser(name = name!!, phone = phone!!, email = email!!, date = date!!)
    }
}

fun Route.getAllUsers(dao: UsersDaoImpl) {
    get("/geAllUsers") {
        call.respond(dao.viewAllUsers())
    }
}

fun Route.updateAUser(dao: UsersDaoImpl) {
    get("/updateAUser") {
        val id = call.parameters["id"]?.toInt() ?: return@get
        val name = call.parameters["name"] ?: return@get
        val phone = call.parameters["phone"] ?: return@get
        val email = call.parameters["email"] ?: return@get
        val date = call.parameters["date"] ?: return@get
        dao.updateUser(id = id, name = name, phone = phone, email = email, date = date)
    }
}

fun Route.removeAUser(dao: UsersDaoImpl){
    get("/deleteAUser") {
        val id = call.parameters["id"]?.toInt() ?: return@get
        dao.removeAUser(id)
    }
}