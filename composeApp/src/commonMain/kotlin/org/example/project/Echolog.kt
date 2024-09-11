package org.example.project

import androidx.compose.runtime.mutableStateOf


val log = mutableStateOf("")

fun myLog(vararg string: String?) {
    val s = string.joinToString(" ")
    println("echo-log $s")
    log.value += s + "\n"
}