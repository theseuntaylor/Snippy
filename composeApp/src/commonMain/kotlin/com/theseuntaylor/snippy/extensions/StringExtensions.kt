package com.theseuntaylor.snippy.extensions

fun String.isEmailValid(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9](.*)(@)(.+)(\\.)(.+)")
    return emailRegex.matches(this)
}