package io.cursedfunction.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
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
    val schoolNameAbbr: String?
)

object Schools : Table() {
    val schoolId = integer("school_id").autoIncrement()
    val schoolName = varchar("full_name", 150)
    val schoolNameAbbr = varchar("abbreviated_name", 10)

    override val primaryKey = PrimaryKey(schoolId, name = "school_id_pk")
}

interface SchoolDao {
    suspend fun getAll(): List<School>
}

class SchoolDaoImpl : SchoolDao {
    override suspend fun getAll(): List<School> = DatabaseFactory.dbQuery {
        Schools.selectAll().map { row ->
            School(
                schoolId = row[Schools.schoolId],
                schoolName = row[Schools.schoolName],
                schoolNameAbbr = row[Schools.schoolNameAbbr]
            )
        }
    }
}