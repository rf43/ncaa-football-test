package io.cursedfunction.data.tables

import org.jetbrains.exposed.sql.Table

object SchoolsTable : Table() {
    val schoolId = integer("school_id").autoIncrement()
    val schoolName = varchar("full_name", 150)
    val schoolNameAbbr = varchar("abbreviated_name", 10)
    val schoolShortName = varchar("short_name", 25)
    val schoolLocationCity = varchar("location_city", 50)
    val schoolLocationState = varchar("location_state", 2)
    val schoolConferenceId = integer("conference_id")
    val logoId = integer("logo_id")

    override val primaryKey = PrimaryKey(schoolId, name = "school_id_pk")
}