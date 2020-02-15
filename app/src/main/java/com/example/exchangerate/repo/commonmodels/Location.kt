package com.example.exchangerate.repo.commonmodels

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)