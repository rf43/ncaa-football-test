package io.cursedfunction.content

import io.cursedfunction.plugins.School
import kotlinx.html.*

fun FlowContent.schoolsScreen(schools: List<School>) {
    val imgSrcList = listOf(
        "454",
        "455",
        "456",
        "457",
        "466",
        "467",
        "468",
        "471",
        "472",
        "474",
        "477",
    )

    h1 {
        classes = setOf("screen-title")
        +"Schools"
    }

    ul {
        schools.forEach { school ->
            li {
                classes = setOf("school-item")
                img {
                    classes = setOf("size-6")
                    src = "img/${imgSrcList.random()}.svg"
                }
                p {
                    +"${school.schoolName} - ${school.schoolNameAbbr}"
                }
            }
        }
    }
}