package com.example.exchangerate.repo.apicreator.gson

import com.example.exchangerate.repo.rates.model.KeyValueResponse
import com.example.exchangerate.repo.rates.model.OrganizationParams
import com.example.exchangerate.repo.rates.model.Rate
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.koin.dsl.module

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

val gsonModule = module {
    single {
        GsonBuilder()
            .registerTypeAdapter(object: TypeToken<KeyValueResponse<String, Rate>>(){}.type, RateDeserializer())
            .registerTypeAdapter(object: TypeToken<KeyValueResponse<String, OrganizationParams>>(){}.type, OrganizationDeserializer())
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setDateFormat(DEFAULT_DATE_FORMAT)
            .enableComplexMapKeySerialization()
            .serializeSpecialFloatingPointValues()
            .disableHtmlEscaping()
            .create()
    }
}