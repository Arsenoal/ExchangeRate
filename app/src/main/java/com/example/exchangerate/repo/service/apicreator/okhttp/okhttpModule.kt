package com.example.exchangerate.repo.service.apicreator.okhttp

import com.example.exchangerate.repo.service.apicreator.entity.ApiProviderConfig
import com.example.exchangerate.repo.service.apicreator.entity.OkHttpConfig
import com.example.exchangerate.repo.service.apicreator.entity.OkHttpProps
import com.example.exchangerate.repo.service.apicreator.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

fun okhttpModule(apiProviderConfig: ApiProviderConfig) = module {

    single {
        OkHttpClient()
    }

    factory { (config: OkHttpConfig) ->
        get<OkHttpClient>().newBuilder().apply {
            connectTimeout(config.connectionTimeout, TimeUnit.SECONDS)
            readTimeout(config.readAndWriteTimeout, TimeUnit.SECONDS)
            writeTimeout(config.readAndWriteTimeout, TimeUnit.SECONDS)

            val interceptors: List<Interceptor> = get()

            interceptors.forEach { interceptor ->
                addInterceptor(interceptor)
            }

            if (config.protocols.isNotEmpty()) protocols(config.protocols)

            if (config.cacheFile != null) cache(Cache(config.cacheFile, OkHttpProps.CACHE_SIZE))

            if (config.interceptors.isNotEmpty()) config.interceptors.forEach { interceptor -> addInterceptor(interceptor) }
        }
    }

    factory(OkHttpProps.DEFAULT_CLIENT) { (config: OkHttpConfig) ->
        get<OkHttpClient.Builder>{ parametersOf(config) }.connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS)).build()
    }

    factory {
        val interceptors = mutableListOf<Interceptor>()
        if (apiProviderConfig.isDebuggableMode) {

            val loggingHeader = HttpLoggingInterceptor()
            loggingHeader.level = HttpLoggingInterceptor.Level.HEADERS
            interceptors.add(loggingHeader)

            val loggingBody = HttpLoggingInterceptor()
            loggingBody.level = HttpLoggingInterceptor.Level.BODY
            interceptors.add(loggingBody)
        }

        if (apiProviderConfig.lazyInitHeadersBlock().isNotEmpty()) {
            interceptors.add(HeaderInterceptor(apiProviderConfig.lazyInitHeadersBlock))
        }

        interceptors.toList()
    }

}