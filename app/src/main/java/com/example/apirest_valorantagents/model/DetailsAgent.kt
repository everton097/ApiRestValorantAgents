package com.example.restapi_dotahero.data

import com.squareup.moshi.Json

data class DetailsApiResponse(
    @Json(name = "status") val status: Int,
    @Json(name = "data") val data: DetailsAgent
)
data class Ability(
    @Json(name = "slot") val slot: String,
    @Json(name = "displayName") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "displayIcon") val icon: String?
)

data class DetailsAgent(
    @Json(name = "uuid") val id: String,
    @Json(name = "displayName") val name: String,
    @Json(name = "displayIcon") val img: String,
    val description: String,
    val bustPortrait: String,
    val isPlayableCharacter: Boolean,
    val abilities: List<Ability>,
)

