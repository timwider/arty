package ru.coralcode.arty.data.cache.database_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.coralcode.arty.data.Mapper
import ru.coralcode.arty.data.cache.RoomListTypeConverter
import ru.coralcode.arty.data.extensions.fromJsonToList
import ru.coralcode.arty.data.models.DataModel
import ru.coralcode.arty.data.models.Artwork as ArtworkData

@Entity
data class Artwork(
    @PrimaryKey var id: Int,
    val title: String,
    val artistTitle: String,
    val artistId: Int,
    val dateDisplay: String,
    var imagePath: String,
    val description: String,
    var publicationHistory: String,
    var exhibitionHistory: String,
    var provenanceText: String,
    var placeOfOrigin: String,
    var mediumDisplay: String,
    @field: TypeConverters(RoomListTypeConverter::class) var categoryTypes: String

): DataModel, Mapper<Artwork, ArtworkData> {

    override fun map(from: Artwork): ArtworkData {
        return ArtworkData(
            id = id,
            title = title,
            artistTitle = artistTitle,
            artistId = artistId,
            dateDisplay = dateDisplay,
            imagePath = imagePath,
            description = description,
            publicationHistory = publicationHistory,
            exhibitionHistory = exhibitionHistory,
            provenanceText = provenanceText,
            placeOfOrigin = placeOfOrigin,
            mediumDisplay = mediumDisplay,
            categoryTypes = categoryTypes.fromJsonToList())
    }
}
