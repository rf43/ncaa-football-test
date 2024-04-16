package io.cursedfunction.content

import io.cursedfunction.data.dto.Conference
import io.cursedfunction.data.dto.Logo
import kotlinx.html.*

fun FlowContent.addSchool(logos: List<Logo>, conferences: List<Conference>) {
    div {
        classes = setOf("h-screen")

        div {
            classes = setOf("mx-auto max-w-7xl px-4 py-24 sm:px-6 sm:py-32 lg:px-8")

            div {
                classes = setOf("mx-auto max-w-2xl")

                form {
                    action = "/insert-school"
                    method = FormMethod.post

                    div {
                        classes = setOf("space-y-12")

                        div {
                            classes = setOf("border-b border-gray-900/10 pb-12")

                            h2 {
                                classes = setOf("text-base font-semibold leading-7 text-gray-900")
                                +"Add School"
                            }

                            p {
                                classes = setOf("mt-1 text-sm leading-6 text-gray-600")
                                +"Some stuff goes here about what it means to add a school"
                            }

                            div {
                                classes = setOf("mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6")

                                // region School Name
                                div {
                                    classes = setOf("sm:col-span-4")

                                    label {
                                        classes = setOf("block text-sm font-medium leading-6 text-gray-900")
                                        htmlFor = "schoolname"
                                        +"School Name"
                                    }

                                    div {
                                        classes = setOf("mt-2")

                                        div {
                                            classes =
                                                setOf(
                                                    "flex rounded-md shadow-sm ring-1 ring-inset ring-slate-300 " +
                                                            "focus:ring-2 focus-within:ring-inset " +
                                                            "focus-within:ring-slate-600 sm:max-w-md"
                                                )

                                            span {
                                                classes =
                                                    setOf("flex select-none items-center pl-3 text-gray-500 sm:text-sm")
                                                +"School Name"
                                            }

                                            input {
                                                id = "schoolname"
                                                name = "schoolname"
                                                type = InputType.text
                                                placeholder = "school name"
                                                classes =
                                                    setOf(
                                                        "block flex-1 border-0 bg-transparent py-1.5 pl-1 " +
                                                                "text-gray-900 placeholder:text-gray-400 " +
                                                                "focus:ring-0 sm:text-sm sm:leading-6 " +
                                                                "focus:outline-none"
                                                    )
                                            }
                                        }
                                    }
                                }
                                // endregion

                                // region About
                                div {
                                    classes = setOf("col-span-full")

                                    label {
                                        classes = setOf("block text-sm font-medium leading-6 text-gray-900")
                                        htmlFor = "about"
                                        +"About"
                                    }

                                    div {
                                        classes = setOf("mt-2")

                                        textArea {
                                            classes = setOf(
                                                "block w-full rounded-md border-0 py-1.5 text-gray-900 " +
                                                        "shadow-sm ring-1 ring-inset ring-slate-300 " +
                                                        "placeholder:text-gray-400 focus:ring-1 focus:ring-inset " +
                                                        "focus:ring-slate-600 sm:text-sm sm:leading-6 " +
                                                        "focus:outline-none"
                                            )
                                            rows = "3"
                                            name = "about"
                                            id = "about"
                                        }
                                    }

                                    p {
                                        classes = setOf("mt-3 text-sm leading-6 text-gray-600")
                                        +"This is just a representation of what a multiline textarea would look like."
                                    }
                                }
                                // endregion
                            }
                        }
                    }
                }
            }
        }
    }
}
