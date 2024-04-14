package io.cursedfunction.content

import io.cursedfunction.plugins.School
import kotlinx.html.*

fun FlowContent.schoolsScreen(schools: List<School>) {
    h1 {
        classes = setOf("screen-title")
        +"Schools"
    }

    ul {
        schools.forEach { school ->
            li {
                classes = setOf("school-item")
                +"${school.schoolName} - ${school.schoolNameAbbr}"
            }
        }
    }
}