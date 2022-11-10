package ru.coralcode.arty.data.models

import ru.coralcode.arty.data.Mapper

class Artwork(
    private val id: Int,
    private val title: String,
    private val artistTitle: String,
    private val artistId: Int,
    private val dateDisplay: String,
    private val imagePath: String,
    private val description: String,
    private var publicationHistory: String,
    private var exhibitionHistory: String,
    private var provenanceText: String,
    private var placeOfOrigin: String,
    private var mediumDisplay: String,
    private var categoryTypes: List<String>

): DataModel