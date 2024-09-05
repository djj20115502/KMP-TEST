package org.example.project.mult

expect fun request(string: String, callBack: (String) -> Unit)


fun requestCover(string: String, callBack: (String) -> Unit) {
    println("requestCover:$string")
    request(string) {
        println("$string  ans:$it")
        callBack(it)
    }
}