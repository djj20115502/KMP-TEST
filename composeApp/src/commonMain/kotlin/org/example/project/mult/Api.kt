package org.example.project.mult

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.example.project.ui.SystemInfo

expect fun request(string: String, callBack: (String) -> Unit)


fun requestCover(string: String, callBack: (String) -> Unit) {
    println("requestCover:$string")
    request(string) {
        println("$string  ans:$it")
        callBack(it)
    }
}

fun requestCover(request: RequestBean, callBack: (ResponseMessage<SystemInfo>) -> Unit) {
    println("requestCover:$request")
    requestCover(Json.encodeToString(request)) {
        myJson.decodeFromString<ResponseBean<SystemInfo>>(it).res?.apply {
            callBack(this)
        }
    }
}

val myJson = Json { ignoreUnknownKeys = true }


/**
 *
 * {
 *        "id": "1",  //由web端創建，由於關聯請求數據和回調。
 *        "needToken": true,  // 是否需要添加token
 *        "path": "v1/employer/system-info",    // api path
 *        "method": "GET",  // or POST
 *        "params": {} //  method為POST時候的data
 * }
 * */
@Serializable
data class RequestBean(

    var id: String? = null,

    var method: String? = null,

    var needToken: Boolean? = null,

    var params: JsonObject? = null,

    var path: String? = null
)

@Serializable
data class ResponseBean<T>(
    var id: String? = null,
    var res: ResponseMessage<T>? = null,
)

//@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class ResponseMessage<T>(
    @SerialName("agreementUpdatedAt")
    var agreementUpdatedAt: Int? = null,
    @SerialName("code")
    var code: String? = null,
    @SerialName("data")
    var data: T? = null,
    @SerialName("id")
    var id: Long? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("optionLastUpdatedAt")
    var optionLastUpdatedAt: Int? = null,
    @SerialName("requestAt")
    var requestAt: Int? = null,
    @SerialName("requestAtMs")
    var requestAtMs: Long? = null,
    @SerialName("responseAt")
    var responseAt: Int? = null,
    @SerialName("responseAtMs")
    var responseAtMs: Long? = null,
    @SerialName("success")
    var success: Boolean? = null,
    @SerialName("title")
    var title: String? = null
) {
    companion object {

        fun error(error: String?): ResponseMessage<String> {
            return error("-1", error)
        }


        fun error(errorCode: String, error: String?): ResponseMessage<String> {
            val rt = ResponseMessage<String>()
            rt.code = errorCode
            rt.message = error
            return rt
        }
    }
}



