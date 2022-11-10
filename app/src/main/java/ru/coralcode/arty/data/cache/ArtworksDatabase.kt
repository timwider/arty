package ru.coralcode.arty.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.coralcode.arty.data.cache.database_models.Artwork
import ru.coralcode.arty.utils.ContextDependentComponent

@Database(entities = [Artwork::class], version = 1)
abstract class ArtworksDatabase: RoomDatabase(), ContextDependentComponent<ArtworksDatabase> {

    private var instance: ArtworksDatabase? = null

    override fun initialize(context: Context): ArtworksDatabase {
        return instance ?: Room.databaseBuilder(
            context,
            ArtworksDatabase::class.java,
            "artworks"
        ).build().also { instance = it }
    }
}
