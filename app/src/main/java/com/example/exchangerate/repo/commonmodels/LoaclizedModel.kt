package com.example.exchangerate.repo.commonmodels

import com.google.gson.annotations.SerializedName

data class LocalizedModel(
    @SerializedName("en") val enLocale: String,
    @SerializedName("am") val amLocale: String,
    @SerializedName("ru") val ruLocale: String
)