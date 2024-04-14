package io.cursedfunction.plugins

import io.cursedfunction.content.schoolsScreen
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.classes
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.serialization.json.Json
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

                    fileName.endsWith(".js") -> {
                        ContentType.Text.JavaScript
                    }

                    fileName.endsWith(".json") -> {
                        ContentType.Text.JavaScript
                    }

                    fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") -> {
                        ContentType.Image.JPEG
                    }

                    else -> {
                        ContentType.Text.Plain
                    }
                }
            }
        }
        get("/") {
            val schoolList = dao.getAll()
            call.respondHtml(status = HttpStatusCode.OK) {
                head {
                    link {
                        href = "css/output.css"
                        rel = "stylesheet"
                    }
                }
                body {
                    classes = setOf("bg-ktor-football")
                    schoolsScreen(schoolList)
                }
            }
        }

        get("/css/{page}") {
            call.parameters["page"]?.let { page ->
                when(page) {
                    "output.css" -> {
                        call.respondText(ContentType.Text.CSS) {
                            File("src/main/resources/static/css/output.css").readText()
                        }
                    }
                }
            }
        }
    }
}
