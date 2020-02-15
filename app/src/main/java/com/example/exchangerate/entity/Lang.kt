package com.example.exchangerate.entity

sealed class Lang(val lang: String)

object EN: Lang("en")

object RU: Lang("ru")

object AM: Lang("am")