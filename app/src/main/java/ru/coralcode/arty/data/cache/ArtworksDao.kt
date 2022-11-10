package ru.coralcode.arty.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.coralcode.arty.data.cache.database_models.Artwork

@Dao
interface ArtworksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheArtworks(list: List<Artwork>)

    @Query("select * from tablename")
    suspend fun getCachedArtworks()

    @Query("delete from tablename")
    suspend fun clearCache()

    @Delete
    suspend fun deleteArtworkFromCache(id: Int)
}