package io.cursedfunction.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Conference(
    val conferenceId: Int,
    val fullName: String,
    val shortName: String,
    val logo: Logo
)
