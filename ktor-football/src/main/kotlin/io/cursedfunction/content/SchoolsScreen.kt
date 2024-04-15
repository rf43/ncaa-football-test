package io.cursedfunction.content

import io.cursedfunction.data.dto.School
import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.schoolsScreen(schools: List<School>) {

    div {

        h1 {
            classes = setOf("text-2xl text-slate-950 pl-4 pt-4")
            +"NCAA Football"
        }

        div {
            classes = setOf("grid grid-cols-[repeat(3,320px)] place-content-center gap-5 mt-10")

            schools.forEach { school ->
                schoolCard(school)
            }

            schools.forEach { school ->
                schoolCard(school)
            }
        }
    }
}