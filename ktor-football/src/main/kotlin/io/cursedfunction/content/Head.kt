package io.cursedfunction.content

import kotlinx.html.HTML
import kotlinx.html.head
import kotlinx.html.link

fun HTML.pageHead() {
    head {
        link {
            href = "css/output.css"
            rel = "stylesheet"
        }
    }
}