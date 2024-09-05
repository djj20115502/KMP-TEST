package org.example.project.mult


/**
 *
 * {
 *        "id": "1",  //由web端創建，由於關聯請求數據和回調。
 *        "needToken": true,  // 是否需要添加token
 *        "path": "v1/employer/system-info",    // api path
 *        "method": "GET",  // or POST
 *        "params": {} //  method為POST時候的data
 * }
 *
 * */
actual fun request(string: String, callBack: (String) -> Unit) {
    console.log("request", string)
    map[string] = callBack
    if (string.isEmpty()) {
        js("javascript:appResponse('XX') ")
    }
    native.webRequest(string)
}

val map = hashMapOf<String, (String) -> Unit>()


//這部分由app本地注入
external class Native {
    fun webRequest(string: String)
}

external val native: Native


/**
 *
 * {
 *        "id": "1", //由web端傳遞過來的id
 *        "res": {}, //後端返回的ResponseMessage  ，如果網絡錯誤，app本地自行創建一個
 * }
 *
 * */

@OptIn(ExperimentalJsExport::class)
@JsExport
fun httpCall(string: String) {
    console.log("httpCall $string ${map.values.size}")
    map.values.firstOrNull()?.run {
        this(string)
    }
    map.clear()
    console.log("httpCall OVER")

}

//data class RE(
//    @SerializedName("id")
//    var id: String? = null,
//    @SerializedName("method")
//    var method: String? = null,
//    @SerializedName("needToken")
//    var needToken: Boolean? = null,
//    @SerializedName("params")
//    var params: Params? = null,
//    @SerializedName("path")
//    var path: String? = null
//)

