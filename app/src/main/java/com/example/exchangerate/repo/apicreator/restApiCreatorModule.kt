package com.example.exchangerate.repo.apicreator

import com.example.exchangerate.repo.apicreator.entity.ApiProviderConfig
import org.koin.dsl.module

val restApiCreatorModule = module {
    single {
        val baseUrl = "https://rate.am/ws/mobile/v2/"

        ApiProviderConfig(baseUrl = baseUrl)
    }

    single {
        RestApiCreator(get())
    }
}