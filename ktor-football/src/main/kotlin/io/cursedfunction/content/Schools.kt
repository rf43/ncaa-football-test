package io.cursedfunction.content

import io.cursedfunction.plugins.School
import kotlinx.html.*

fun FlowContent.schoolsScreen(schools: List<School>) {
    val textColorLightMode = "text-cyan-950"
    val textColorDarkMode = "text-cyan-50"

    h1 {
        classes = setOf("$textColorLightMode dark:$textColorDarkMode")
        +"Schools"
    }

    div {
        classes = setOf("mx-8 my-2")

        schools.forEach { school ->
            div {
                classes = setOf("flex flex-row gap-4 my-8 p-4 hover:bg-slate-200 hover:dark:bg-slate-700 items-center")

                img {
                    classes = setOf("size-10")
                    src = "img/${school.logoToken}.svg"
                }
                p {
                    classes = setOf("$textColorLightMode dark:${textColorDarkMode}")

                    span {
                        classes = setOf("font-extrabold")
                        +"Long Name: "
                    }
                    +school.schoolName
                }
                p {
                    classes = setOf("$textColorLightMode dark:${textColorDarkMode}")

                    span {
                        classes = setOf("font-extrabold")
                        +"Short Name: "
                    }
                    +school.schoolShortName
                }

                school.schoolNameAbbr?.let {
                    p {
                        classes = setOf("$textColorLightMode dark:${textColorDarkMode}")

                        span {
                            classes = setOf("font-extrabold")
                            +"Abbreviation: "
                        }
                        +it
                    }
                }

                p {
                    classes = setOf("$textColorLightMode dark:${textColorDarkMode}")

                    span {
                        classes = setOf("font-extrabold")
                        +"Location-City: "
                    }
                    +school.schoolLocationCity
                }
                p {
                    classes = setOf("$textColorLightMode dark:${textColorDarkMode}")

                    span {
                        classes = setOf("font-extrabold")
                        +"Location-State: "
                    }
                    +school.schoolLocationState
                }
            }
        }
    }

    div {
        classes = setOf("m-12")

        button {
            id = "myButton"
            classes = setOf(
                "rounded-lg px-4 py-2 " +
                        "bg-slate-700 text-slate-50 hover:bg-slate-400 hover:text-slate-700 " +
                        "dark:bg-slate-600 dark:text-slate-50 hover:dark:bg-slate-700 hover:dark:text-slate-400"
            )
            type = ButtonType.button

            +"Click Me"
        }

        p {
            id = "demo"
            classes = setOf("$textColorLightMode dark:$textColorDarkMode")
        }
    }

    script {
        src = "script/demo.js"
    }
}