package io.cursedfunction.plugins

import io.cursedfunction.content.addSchool
import io.cursedfunction.content.pageHead
import io.cursedfunction.content.schoolsScreen
import io.cursedfunction.data.dao.SchoolDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.classes
import kotlinx.html.div
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
            val schoolList = dao.fetchAllSchools()
            call.respondHtml(status = HttpStatusCode.OK) {
                pageHead()
                body {
                    classes = setOf("bg-zinc-200")
                    div {
                        classes = setOf("pb-8")
//                        attributes["style"] = """background-image: url('img/background.jpg')"""

                        schoolsScreen(schoolList)
                    }
                }
            }
        }

        get("/add-school") {

//            val addSchool = dao.addSchool(
//                School(
//                    schoolId = 0,
//                    schoolName = "University of South Florida",
//                    schoolNameAbbr = "USF",
//                    schoolShortName = "South Florida",
//                    schoolLocationCity = "Tampa",
//                    schoolLocationState = "FL",
//                    schoolConference = dao.fetchConference(7),
//                    logo = dao.fetchLogo(19)
//                )
//            )
//
//            println("RF43: addSchool Result => $addSchool")
            val logos = dao.fetchAllLogos()
            val conferences = dao.fetchAllConferences()

            call.respondHtml {
                pageHead()
                body {
                    classes = setOf("bg-zinc-200")

                    addSchool(
                        logos = logos,
                        conferences = conferences
                    )
                }
            }
        }

        post("/insert-school") {
            val params = call.receiveParameters()
            println("RF43: incoming => $params")
            println("RF43: SchoolName => ${params["schoolName"]}")
        }
    }
}
