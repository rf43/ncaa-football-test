package io.cursedfunction.data.tables

import org.jetbrains.exposed.sql.Table

object ConferencesTable : Table() {
    val conferenceId = integer("conference_id").autoIncrement()
    val fullName = varchar("full_name", 150)
    val shortName = varchar("short_name", 150)
    val logoId = integer("logo_id")
    val conferenceTypeId = integer("conf_type_id")

    override val primaryKey = PrimaryKey(conferenceId, name = "conference_id")
}