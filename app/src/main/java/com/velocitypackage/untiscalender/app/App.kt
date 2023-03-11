package com.velocitypackage.untiscalender.app

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.*

fun main()
{
    embeddedServer(Netty, port = 8080, module = Application::module).start(true)
}

fun Application.module()
{
    install(CallLogging) {
        level = Level.INFO
    }
    routing {
        get("/ical") {
            if (!call.request.queryParameters["token"].equals(Config.token)) return@get call.respond(
                HttpStatusCode.Unauthorized,
                "Wrong access token!"
            )
            return@get call.respond(HttpStatusCode.OK, getUserSpecificTable().getCalender().toString())
        }
        get("/debug") {
            return@get call.respond(HttpStatusCode.OK, Config.token)
        }
    }
}