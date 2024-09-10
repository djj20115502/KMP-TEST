package org.example.project.mult

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.example.project.EchoLog


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
    console.log("request", string)
    val id = string.getId()
    map[id] = callBack
    EchoLog.log(id)
    if (string.isEmpty()) {
        js("javascript:appResponse('XX') ")
    }
    GlobalScope.launch {
        delay(15 * 1000)
        map.remove(id)
    }
    native.webRequest(string)
}

val map = hashMapOf<String, (String) -> Unit>()

fun String.getId(): String {
    return try {
        EchoLog.log(("data: " +this))
        Json.parseToJsonElement(this ).jsonObject["id"].toString()
    } catch (e: Throwable) {
        EchoLog.log(("error: " + e.message))
        ""
    }
}

//這部分由app本地注入
external class Native {
    fun webRequest(string: String)
}

external val native: Native

@OptIn(ExperimentalJsExport::class)
@JsExport
fun httpCall(string: String) {
    val id = string.getId()
    console.log("httpCall id $id $string ${map.values.size}")
    map[id]?.apply {
        this(string)
        map.remove(id)
    }
}



