package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement

@Serializable
data class SeaLightsCoverageResponse(
    @SerialName("projectId") val projectId: String,
    @SerialName("coveragePercentage") val coveragePercentage: Double,
    @SerialName("reportDetails") val reportDetails: Map<String, JsonElement> = emptyMap()
)