package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import java.io.File

fun Route.clickjackingTestRoute() {
    get("/test") {
        val file = File("./static/test.html")
        call.respondFile(file)
    }

    get("") {
        val file = File("./static/trap.html")
        call.respondFile(file)
    }
}
