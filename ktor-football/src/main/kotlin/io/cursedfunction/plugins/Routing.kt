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

                    fileName.endsWith("svg") -> {
                        ContentType.Image.SVG
                    }

                    else -> {
                        ContentType.Text.Plain
                    }
                }
            }
        }

        staticFiles("/img", File("src/main/resources/static/img"))
        staticFiles("/css", File("src/main/resources/static/css"))

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
    }
}
