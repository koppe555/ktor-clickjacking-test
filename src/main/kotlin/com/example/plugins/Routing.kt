package com.example.plugins

import com.example.routes.clickjackingTestRoute
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        clickjackingTestRoute()
    }
}
