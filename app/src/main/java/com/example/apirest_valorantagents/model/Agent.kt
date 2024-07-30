package com.example.restapi_dotahero.data

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "status") val status: Int,
    @Json(name = "data") val data: List<Agent>
)

data class Agent(
    @Json(name = "uuid") val id: String,
    @Json(name = "displayIcon") val img: String,
    @Json(name = "displayName") val name: String,
    val isPlayableCharacter: Boolean,
)

