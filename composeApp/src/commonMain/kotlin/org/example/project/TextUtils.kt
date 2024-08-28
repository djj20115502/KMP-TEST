package org.example.project
fun String?.isEmpty(): Boolean {
    if (this == null) {
        return true
    }
    if (this.length==0) {
        return true
    }
    return false
}