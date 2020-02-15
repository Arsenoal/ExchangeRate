package com.example.exchangerate.presentation.common.entity

sealed class ERStatus

object ERSuccess: ERStatus()

sealed class ERError: ERStatus()

object ERUserInputError: ERError()

object ERAppError: ERError()

object ERThirdPartyError: ERError()