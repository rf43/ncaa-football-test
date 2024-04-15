package io.cursedfunction.data.dao

import io.cursedfunction.data.dto.Conference
import io.cursedfunction.data.dto.Logo
import io.cursedfunction.data.dto.School
import io.cursedfunction.data.tables.ConferencesTable
import io.cursedfunction.data.tables.LogosTable
import io.cursedfunction.data.tables.SchoolsTable
import io.cursedfunction.plugins.DatabaseFactory
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class SchoolDaoImpl : SchoolDao {
    override suspend fun fetchAllSchools(): List<School> = DatabaseFactory.dbQuery {
        SchoolsTable.selectAll().map { row ->
            School(
                schoolId = row[SchoolsTable.schoolId],
                schoolName = row[SchoolsTable.schoolName],
                schoolNameAbbr = row[SchoolsTable.schoolNameAbbr],
                schoolShortName = row[SchoolsTable.schoolShortName],
                schoolLocationCity = row[SchoolsTable.schoolLocationCity],
                schoolLocationState = row[SchoolsTable.schoolLocationState],
                schoolConference = fetchConference(row[SchoolsTable.schoolConferenceId]),
                logoToken = fetchLogo(row[SchoolsTable.logoId]).logoToken
            )
        }
    }

    override suspend fun fetchConference(id: Int): Conference {
        ConferencesTable.select {
            ConferencesTable.conferenceId eq id
        }.single().let { resultRow ->
            return Conference(
                conferenceId = resultRow[ConferencesTable.conferenceId],
                fullName = resultRow[ConferencesTable.fullName],
                shortName = resultRow[ConferencesTable.shortName],
                logo = fetchLogo(resultRow[ConferencesTable.logoId])
            )
        }
    }

    override suspend fun fetchLogo(id: Int): Logo {
        LogosTable.select {
            LogosTable.logoId eq id
        }.single().let { resultRow ->
            return Logo(
                logoId = resultRow[LogosTable.logoId],
                logoToken = resultRow[LogosTable.logoToken]
            )
        }
    }
}