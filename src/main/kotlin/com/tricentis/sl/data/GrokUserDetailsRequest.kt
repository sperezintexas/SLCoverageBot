package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GrokUserDetailsRequest(
    @SerialName("query") val query: String
)