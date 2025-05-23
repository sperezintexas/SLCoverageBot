package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SeaLightsCoverageRequest(
    @SerialName("projectId") val projectId: String,
    @SerialName("reportType") val reportType: String = "coverage"
)