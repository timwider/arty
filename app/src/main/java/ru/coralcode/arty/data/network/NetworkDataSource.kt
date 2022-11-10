package ru.coralcode.arty.data.network

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.toList

class NetworkDataSource(
    private val api: ArtworksApi,
    private val scope: CoroutineScope
) {

}