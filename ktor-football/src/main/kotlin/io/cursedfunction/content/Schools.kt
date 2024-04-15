package io.cursedfunction.content

import io.cursedfunction.data.dto.School
import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.schoolsScreen(schools: List<School>) {

    h1 {
        classes = setOf("my-4 ms-8 text-2xl text-cyan-950")
        +"NCAA Football"
    }

    div {
        classes = setOf("grid grid-cols-[repeat(3,320px)] place-content-center gap-5")

        schools.forEach { school ->
            schoolCard(school)
        }
    }
}