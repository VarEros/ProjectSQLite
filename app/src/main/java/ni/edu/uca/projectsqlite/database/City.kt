package ni.edu.uca.projectsqlite.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblCity")
data class City (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="idCity") val id: Int,
        @ColumnInfo(name="Name") val name: String,
        @ColumnInfo(name="Capital") val capital: String
        )