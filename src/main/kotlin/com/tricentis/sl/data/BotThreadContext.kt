package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BotThreadContext(
    @SerialName("threadTs") val threadTs: String,
    @SerialName("channelId") val channelId: String,
    @SerialName("userId") val userId: String,
    @SerialName("lastMessage") val lastMessage: String? = null
)