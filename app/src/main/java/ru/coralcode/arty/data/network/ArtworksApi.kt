package ru.coralcode.arty.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.coralcode.arty.data.models.ArtworksResponse

private const val API_LINK = "https://api.artic.edu/api/v1"

interface ArtworksApi {

    @GET("artworks")
    fun fetchData(): ArtworksResponse

}