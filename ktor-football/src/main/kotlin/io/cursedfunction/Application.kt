package io.cursedfunction

import io.cursedfunction.data.dao.SchoolDaoImpl
import io.cursedfunction.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(
        dbUsername = environment.config.property("ktor.database.user").getString(),
        dbSchema = environment.config.property("ktor.database.schema").getString()
    )

    configureHTTP()
    configureSerialization()
    configureTemplating()
    configureRouting(
        dao = SchoolDaoImpl()
    )
}
