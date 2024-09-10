package org.example.project.ui
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class SystemInfo(
    @SerialName("api_urls")
    var activities: List<ApiUrlBean?>? = null
)

@Serializable
data class ApiUrlBean(
    @SerialName("url_name")
    var urlName: String? = null,
    @SerialName("url")
    var url: String? = null,
)