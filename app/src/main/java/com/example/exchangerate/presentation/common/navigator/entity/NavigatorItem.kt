package com.example.exchangerate.presentation.common.navigator.entity

sealed class NavigatorItem

object Transaction: NavigatorItem()

object ExchangeRate: NavigatorItem()

object Calculator: NavigatorItem()

object More: NavigatorItem()