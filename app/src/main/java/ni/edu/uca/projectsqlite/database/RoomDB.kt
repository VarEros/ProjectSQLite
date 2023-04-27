package ni.edu.uca.projectsqlite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [City::class], version = 1, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {

    abstract fun cityDao(): CityDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "city_database"
                ).addCallback(CityDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class CityDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cityDao())
                }
            }
        }

        suspend fun populateDatabase(cityDAO: CityDAO) {
            // Delete all content here.
            cityDAO.deleteAll()

            // Add sample words.
            cityDAO.insert(City(1, "Alemania", "Berlin"))
            cityDAO.insert(City(2, "Argentina", "Buenos aires"))
            // TODO: Add your own words!
        }
    }
}