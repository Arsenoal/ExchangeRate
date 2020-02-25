package com.example.exchangerate.common

import java.lang.RuntimeException

class ExchangeRateException(message: String = String.empty()): RuntimeException(message)