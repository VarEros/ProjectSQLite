package ni.edu.uca.projectsqlite.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDAO {
    @Query("SELECT * FROM tblCity")
    fun getAll(): Flow<List<City>>

    @Query("SELECT * FROM tblCity WHERE idCity = :id")
    suspend fun get(id: Int): City

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Update
    suspend fun update(city: City)

    @Delete
    suspend fun delete(city: City)

    @Query("DELETE FROM tblCity")
    suspend fun deleteAll()
}