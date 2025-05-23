package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
data class GrokUserDetailsResponse(
    @SerialName("userId") val userId: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String? = null,
    @SerialName("details") val details: Map<String, JsonElement> = emptyMap()
)
