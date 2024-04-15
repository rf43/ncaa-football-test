package io.cursedfunction.content

import io.cursedfunction.data.dto.School
import kotlinx.html.*

fun FlowContent.schoolCard(school: School) {
    div {
        id = "schoolCard"
        classes = setOf(
            "bg-slate-600 w-80 h-96 rounded-t-lg cursor-pointer " +
                    "shadow-md hover:shadow-lg " +
                    "transition ease-in-out delay-100 hover:-translate-y-1 hover:scale-105 duration-300"
        )

        div {
            classes = setOf("relative bg-slate-50 w-full h-52 rounded-t-lg")

            div {
                classes = setOf("static")
                img {
                    classes = setOf("absolute top-1 right-2 size-12")
                    src = "img/${school.schoolConference.logo.logoToken}.svg"
                }
            }

            img {
                classes = setOf("size-28 w-full mb-2 pt-2")
                src = "img/${school.logoToken}.svg"
            }

            p {
                classes = setOf("px-8 text-slate-950 text-center font-semibold")

                +school.schoolName
            }
        }

        div {
            classes = setOf("w-full h-5 bg-slate-900")

            p {
                classes = setOf("text-xs text-slate-50 text-end mr-2 mt-1")

                +"${school.schoolLocationCity}, ${school.schoolLocationState}"
            }
        }

        div {
            classes = setOf("flex h-36 place-content-center")

            p {
                classes = setOf("flex items-center text-4xl font-bold text-slate-50 drop-shadow-md")

                +"0 - 0"

                span {
                    classes = setOf("text-base font-light ms-2")

                    +"(0 - 0)"
                }
            }
        }
    }
}