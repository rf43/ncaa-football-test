package io.cursedfunction.plugins

import io.cursedfunction.content.pageHead
import io.cursedfunction.content.schoolsScreen
import io.cursedfunction.data.dao.SchoolDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.classes
import java.io.File

fun Application.configureRouting(dao: SchoolDao) {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        staticResources("/", "static") {
            preCompressed(CompressedFileType.GZIP)
            contentType { url ->
                val fileName: String = url.file.lowercase()

                when {
                    fileName.endsWith(".css") -> {
                        ContentType.Text.CSS
                    }

                    fileName.endsWith(".js") || fileName.endsWith(".json") -> {
                        ContentType.Text.JavaScript
                    }

                    fileName.endsWith("svg") -> {
                        ContentType.Image.SVG
                    }

                    else -> {
                        ContentType.Text.Plain
                    }
                }
            }
        }

        staticFiles(
            remotePath = "/img",
            dir = File("src/main/resources/static/img")
        )
        staticFiles(
            remotePath = "/css",
            dir = File("src/main/resources/static/css")
        )
        staticFiles(
            remotePath = "/script",
            dir = File("src/main/resources/static/script")
        )

        get("/") {
            val schoolList = dao.getAllSchools()
            call.respondHtml(status = HttpStatusCode.OK) {
                pageHead()
                body {
                    classes = setOf("bg-slate-100")
                    schoolsScreen(schoolList)
                }
            }
        }
    }
}
