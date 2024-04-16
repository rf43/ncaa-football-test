package io.cursedfunction.content

import io.cursedfunction.data.dto.School
import kotlinx.html.*

fun FlowContent.schoolsScreen(schools: List<School>) {

    div {

        h1 {
            classes = setOf("text-2xl text-slate-950 pl-4 pt-4")
            +"NCAA Football"
        }

        a {
            classes = setOf("text-xl text-slate-950 hover:text-cyan-600 pl-4 pt-4")
            href = "/add-school"

            +"Add a School"
        }

        div {
            classes = setOf("grid grid-cols-[repeat(3,320px)] place-content-center gap-5 mt-10")

            schools.forEach { school ->
                schoolCard(school)
            }
        }
    }
}
