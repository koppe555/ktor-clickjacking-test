package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*



fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()

    install(DefaultHeaders) {
        header("X-Frame-Options", "DENY")
    }
}

