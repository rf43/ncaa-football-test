package io.cursedfunction.content

import io.cursedfunction.plugins.School
import kotlinx.html.*
import java.io.File

fun FlowContent.schoolsScreen(schools: List<School>) {
    val files = File("src/main/resources/static/img")
        .listFiles()?.filter { it.isFile }?.map {
            it.name.substringAfter(delimiter = "/img" )
        } ?: emptyList()

    h1 {
        classes = setOf("screen-title")
        +"Schools"
    }

    div {
        classes = setOf("mx-4")

        schools.forEach { school ->
            div {
                classes = setOf("flex flex-row gap-4 my-8")

                img {
                    classes = setOf("size-6")
                    src = "img/${school.logoToken}.svg"
                }
                p {
                    classes = setOf("text-cyan-950")
                    +"${school.schoolName} - ${school.schoolNameAbbr}"
                }
            }
        }
    }
}