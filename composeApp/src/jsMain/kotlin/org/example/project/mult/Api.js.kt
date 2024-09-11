package org.example.project.mult

import kotlinx.browser.window
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.example.project.myLog
import org.w3c.dom.get


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
@OptIn(DelicateCoroutinesApi::class)
actual fun request(string: String, callBack: (String) -> Unit) {
    myLog("request", string)
    val id = string.getId()
    map[id] = callBack
    myLog(id)
    if (string.isEmpty()) {
        return
    }
    GlobalScope.launch {
        delay(15 * 1000)
        map.remove(id)
    }
    try {
        callNative(id, string)
    } catch (e: Throwable) {
        myLog(e.message)
    }
}

val map = hashMapOf<String, (String) -> Unit>()
val android: dynamic = window["native"]?.webRequest
val ios: dynamic = window["webkit"]?.messageHandlers?.webRequest?.postMessage
fun callNative(id: String, string: String) {
    myLog("android  " + jsTypeOf(android))
    myLog("android  " + (jsTypeOf(android) == "function"))
    myLog("ios  " + jsTypeOf(ios))
    try {
        //這裏必須這樣寫，不然 會報錯  java bridge method can't be invoked on a non injected object
        if (jsTypeOf(android) == "function") {
            window["native"]?.webRequest(string)
        } else if (jsTypeOf(ios) == "function") {
            window["webkit"]?.messageHandlers?.webRequest?.postMessage(string)
        } else {
            httpCall("{\"id\" :\"$id\" , \"res\"  : ${Json.encodeToString(ResponseMessage.error("undefined data"))}}")
        }
    } catch (e: Throwable) {
        myLog(e.message)
        httpCall("{\"id\"  :\"$id\" , \"res\"   : ${Json.encodeToString(ResponseMessage.error("undefined"))}}")
    }
}

/**
 *
 * {
 *        "id": "1", //由web端傳遞過來的id
 *        "res": {}, //後端返回的ResponseMessage  ，如果網絡錯誤，app本地自行創建一個
 * }
 * */

fun String.getId(): String {
    return try {
        myLog(("data: $this"))
        Json.parseToJsonElement(this).jsonObject["id"].toString()
    } catch (e: Throwable) {
        myLog(("error: " + e.message))
        ""
    }
}

////這部分由app本地注入
//external class Native {
//    fun webRequest(string: String)
//}
//
//external val native: Native

@OptIn(ExperimentalJsExport::class)
@JsExport
fun httpCall(string: String) {
    val id = string.getId()
    map[id]?.apply {
        this(string)
        map.remove(id)
    }
}


