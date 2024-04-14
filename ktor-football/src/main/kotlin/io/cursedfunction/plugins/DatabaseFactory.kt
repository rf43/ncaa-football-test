package io.cursedfunction.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/ncaa_football",
            driver = "org.postgresql.Driver",
            user = "cursedfunction",
            setupConnection = { connection ->
                connection.schema = "schema_main"
            }
        )
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}

@Serializable
data class School(
    val schoolId: Int,
    val schoolName: String,
    val schoolNameAbbr: String?,
    val logoToken: String
)

@Serializable
data class Logo(
    val logoId: Int,
    val logoToken: String
)

object SchoolsTable : Table() {
    val schoolId = integer("school_id").autoIncrement()
    val schoolName = varchar("full_name", 150)
    val schoolNameAbbr = varchar("abbreviated_name", 10)
    val logoId = integer("logo_id")

    override val primaryKey = PrimaryKey(schoolId, name = "school_id_pk")
}

object LogosTable: Table() {
    val logoId = integer("logo_id").autoIncrement()
    val logoToken = varchar("logo_token", 10)

    override val primaryKey = PrimaryKey(logoId, name = "logos_pk")
}

interface SchoolDao {
    suspend fun getAll(): List<School>
    suspend fun getLogoToken(id: Int): String
}

class SchoolDaoImpl : SchoolDao {
    override suspend fun getAll(): List<School> = DatabaseFactory.dbQuery {
        SchoolsTable.selectAll().map { row ->
            School(
                schoolId = row[SchoolsTable.schoolId],
                schoolName = row[SchoolsTable.schoolName],
                schoolNameAbbr = row[SchoolsTable.schoolNameAbbr],
                logoToken = getLogoToken(row[SchoolsTable.logoId])
            )
        }
    }

    override suspend fun getLogoToken(id: Int): String = DatabaseFactory.dbQuery {
        LogosTable.select {
            LogosTable.logoId eq id
        }.single()[LogosTable.logoToken]
    }
}