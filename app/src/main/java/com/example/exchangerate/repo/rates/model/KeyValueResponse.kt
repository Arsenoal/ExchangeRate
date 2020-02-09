package com.example.exchangerate.repo.rates.model

data class KeyValueResponse<K, V>(
    var data: Map<K, V> = mutableMapOf()
)