package io.cursedfunction.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    val logoId: Int,
    val logoToken: String
)