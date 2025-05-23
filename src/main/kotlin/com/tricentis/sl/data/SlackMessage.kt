package com.tricentis.sl.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SlackMessage(
    @SerialName("text") val text: String,
    @SerialName("channel_id") val channelId: String,
    @SerialName("thread_ts") val threadTs: String? = null
)
