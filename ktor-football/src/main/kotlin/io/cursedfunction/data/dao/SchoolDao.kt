package io.cursedfunction.data.dao

import io.cursedfunction.data.dto.Conference
import io.cursedfunction.data.dto.Logo
import io.cursedfunction.data.dto.School

interface SchoolDao {
    suspend fun getAllSchools(): List<School>
    suspend fun getLogo(id: Int): Logo
    suspend fun getConference(id: Int): Conference
}