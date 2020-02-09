package com.example.exchangerate.repo.rates.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)