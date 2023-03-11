package com.velocitypackage.untiscalender.app

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(vararg args : String)
{
    Config.defaultRoomAlias = args[0]
    Config.defaultTeacherAlias = args[1]
    Config.defaultSummary = args[2]
    Config.username = args[3]
    Config.password = args[4]
    Config.server = args[5]
    Config.school = args[6]
    Config.ssl = args[7] == "true"
    Config.token = args[8]
    embeddedServer(Netty, port = 8080, module = Application::module)
}

fun Application.module()
{
    routing {
        get("/by/{token}") {
            if (call.parameters["token"] != Config.token) return@get call.respond(HttpStatusCode.Unauthorized, "Wrong access token!")
            call.respond(HttpStatusCode.OK, getUserSpecificTable().getCalender().toString())
        }
    }
}