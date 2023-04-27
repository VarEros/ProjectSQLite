package ni.edu.uca.projectsqlite

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ni.edu.uca.projectsqlite.database.CityRepository
import ni.edu.uca.projectsqlite.database.RoomDB

class CityApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { CityRepository(database.cityDao()) }
}