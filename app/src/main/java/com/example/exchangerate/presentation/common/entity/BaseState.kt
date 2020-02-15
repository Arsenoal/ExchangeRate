package com.example.exchangerate.presentation.common.entity

open class BaseState<RESULT>(
        val status: ERStatus,
        val result: RESULT
)