package com.example.exchangerate.repo.service.apicreator.entity

import okhttp3.Interceptor
import okhttp3.Protocol
import java.io.File

data class OkHttpConfig(val connectionTimeout: Long = OkHttpProps.CONNECTION_TIMEOUT,
                        val readAndWriteTimeout: Long = OkHttpProps.READ_WRITE_TIMEOUT,
                        val protocols: List<Protocol> = listOf(),
                        val interceptors: List<Interceptor> = listOf(),
                        val cacheFile: File? = null)