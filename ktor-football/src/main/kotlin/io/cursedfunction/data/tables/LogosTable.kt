package io.cursedfunction.data.tables

import org.jetbrains.exposed.sql.Table

object LogosTable : Table() {
    val logoId = integer("logo_id").autoIncrement()
    val logoToken = varchar("logo_token", 10)

    override val primaryKey = PrimaryKey(logoId, name = "logos_pk")
}