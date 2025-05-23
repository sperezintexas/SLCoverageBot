package com.tricentis.sl.data
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SlackEvent(
    @SerialName("type") val type: String,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("thread_ts") val threadTs: String? = null,
    @SerialName("event_ts") val eventTs: String? = null,

)
