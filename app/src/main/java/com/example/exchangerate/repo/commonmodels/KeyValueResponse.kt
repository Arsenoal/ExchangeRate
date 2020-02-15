package com.example.exchangerate.repo.commonmodels

data class KeyValueResponse<K, V>(
    var data: Map<K, V> = mutableMapOf()
)