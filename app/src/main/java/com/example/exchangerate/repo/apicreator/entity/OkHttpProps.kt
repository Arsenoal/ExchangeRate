package com.example.exchangerate.repo.apicreator.entity

import org.koin.core.qualifier.named

object OkHttpProps {
    const val CACHE_SIZE = 10 * 1024 * 1024L

    const val CONNECTION_TIMEOUT = 10L
    const val READ_WRITE_TIMEOUT = 10L

    val DEFAULT_CLIENT = named("default_client")
}