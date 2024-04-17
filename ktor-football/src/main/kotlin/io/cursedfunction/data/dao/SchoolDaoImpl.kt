package io.cursedfunction.data.dao

import io.cursedfunction.data.dto.Conference
import io.cursedfunction.data.dto.Logo
import io.cursedfunction.data.dto.School
import io.cursedfunction.data.tables.ConferencesTable
import io.cursedfunction.data.tables.LogosTable
import io.cursedfunction.data.tables.SchoolsTable
import io.cursedfunction.plugins.DatabaseFactory
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class SchoolDaoImpl : SchoolDao {
    override suspend fun fetchAllSchools(): List<School> = DatabaseFactory.dbQuery {
        SchoolsTable.selectAll().map { row ->
            resultRowToSchool(row)
        }
    }

    override suspend fun fetchAllLogos(): List<Logo> = DatabaseFactory.dbQuery {
        LogosTable.selectAll().map { row ->
            resultRowToLogo(row)
        }
    }

    override suspend fun fetchAllConferences(): List<Conference> = DatabaseFactory.dbQuery {
        ConferencesTable.selectAll().map { row ->
            resultRowToConference(row)
        }
    }

    override suspend fun fetchSchool(id: Int): School? = DatabaseFactory.dbQuery {
        SchoolsTable.select {
            SchoolsTable.schoolId eq id
        }.singleOrNull()?.let { resultRow ->
            resultRowToSchool(resultRow)
        }
    }

    override suspend fun fetchConference(id: Int): Conference? = DatabaseFactory.dbQuery {
        ConferencesTable.select {
            ConferencesTable.conferenceId eq id
        }.singleOrNull()?.let { resultRow ->
            resultRowToConference(resultRow)
        }
    }

    override suspend fun fetchLogo(id: Int): Logo? = DatabaseFactory.dbQuery {
        LogosTable.select {
            LogosTable.logoId eq id
        }.singleOrNull()?.let { resultRow ->
            resultRowToLogo(resultRow)
        }
    }

    override suspend fun addSchool(school: School): Int? = DatabaseFactory.dbQuery {
        val insertStatement = SchoolsTable.insert {
            it[schoolName] = school.schoolName
            it[schoolNameAbbr] = school.schoolNameAbbr ?: ""
            it[schoolShortName] = school.schoolShortName
            it[schoolLocationCity] = school.schoolLocationCity
            it[schoolLocationState] = school.schoolLocationState
            it[schoolConferenceId] = school.schoolConference?.conferenceId ?: -1
            it[logoId] = school.logo?.logoId ?: -1
        }

        val result = insertStatement.resultedValues?.singleOrNull()?.let { resultRow ->
            resultRowToSchool(resultRow)
        }

        result?.schoolId
    }

    private suspend fun resultRowToSchool(row: ResultRow) = School(
        schoolId = row[SchoolsTable.schoolId],
        schoolName = row[SchoolsTable.schoolName],
        schoolNameAbbr = row[SchoolsTable.schoolNameAbbr],
        schoolShortName = row[SchoolsTable.schoolShortName],
        schoolLocationCity = row[SchoolsTable.schoolLocationCity],
        schoolLocationState = row[SchoolsTable.schoolLocationState],
        schoolConference = fetchConference(row[SchoolsTable.schoolConferenceId]),
        logo = fetchLogo(row[SchoolsTable.logoId])
    )

    private suspend fun resultRowToConference(row: ResultRow) = Conference(
        conferenceId = row[ConferencesTable.conferenceId],
        fullName = row[ConferencesTable.fullName],
        shortName = row[ConferencesTable.shortName],
        logo = fetchLogo(row[ConferencesTable.logoId])
    )

    private suspend fun resultRowToLogo(row: ResultRow) = Logo(
        logoId = row[LogosTable.logoId],
        logoToken = row[LogosTable.logoToken]
    )
}
