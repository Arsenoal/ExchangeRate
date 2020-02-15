package com.example.exchangerate.repo.service.apicreator.retorift

import com.example.exchangerate.repo.service.apicreator.entity.ApiProviderConfig
import com.example.exchangerate.repo.service.apicreator.entity.OkHttpConfig
import com.example.exchangerate.repo.service.apicreator.entity.OkHttpProps
import okhttp3.OkHttpClient
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal fun Scope.createOkHttpClient(config: OkHttpConfig = DefaultOkHttpConfig())
        = get<OkHttpClient>(OkHttpProps.DEFAULT_CLIENT) { parametersOf(config) }

private typealias DefaultOkHttpConfig = OkHttpConfig

fun retrofitModule(apiProviderConfig: ApiProviderConfig) = module {
    single<Converter.Factory> { GsonConverterFactory.create(get()) }

    single {
        Retrofit.Builder().apply {
            baseUrl(apiProviderConfig.baseUrl)
            addConverterFactory(get())
        }
    }

    factory {
        get<Retrofit.Builder>().client(createOkHttpClient()).build()
    }
}