package io.cursedfunction.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class School(
    val schoolId: Int,
    val schoolName: String,
    val schoolNameAbbr: String?,
    val schoolShortName: String,
    val schoolLocationCity: String,
    val schoolLocationState: String,
    val schoolConference: Conference,
    val logoToken: String
)