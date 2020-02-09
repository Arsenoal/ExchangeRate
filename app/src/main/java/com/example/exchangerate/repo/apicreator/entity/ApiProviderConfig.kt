package com.example.exchangerate.repo.apicreator.entity

typealias LazyInitHeadersBlock = () -> Map<String, String>

data class ApiProviderConfig(
    val baseUrl: String,
    val lazyInitHeadersBlock: LazyInitHeadersBlock = { mapOf() },
    val isDebuggableMode: Boolean = true
)