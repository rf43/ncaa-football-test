package io.cursedfunction.data.dao

import io.cursedfunction.data.dto.Conference
import io.cursedfunction.data.dto.Logo
import io.cursedfunction.data.dto.School

interface SchoolDao {
    suspend fun fetchAllSchools(): List<School>
    suspend fun fetchLogo(id: Int): Logo
    suspend fun fetchConference(id: Int): Conference
}