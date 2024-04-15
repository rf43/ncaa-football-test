package io.cursedfunction.plugins

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
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