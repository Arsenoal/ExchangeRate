package com.example.exchangerate.repo.commonmodels

import com.example.exchangerate.common.empty
import com.example.exchangerate.entity.AM
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.RU
import com.google.gson.annotations.SerializedName

data class OrganizationParams(
    @SerializedName("head") val head: Int,
    @SerializedName("title") val titleLocalized: LocalizedModel,
    @SerializedName("address") val addressLocalized: LocalizedModel,
    @SerializedName("location") val location: Location,
    @SerializedName("contacts") val contacts: String,
    @SerializedName("workhours") val workHours: List<WorkHours> = listOf()
)

data class WorkHours(
    @SerializedName("days") val days: String,
    @SerializedName("hours") val hours: String
)

fun OrganizationParams.toEntity(
    organizationId: String,
    organizationName: String,
    lang: Lang): com.example.exchangerate.entity.rates.OrganizationParams {

    var cityLocation = String.empty()
    var address = String.empty()

    when(lang) {
        EN -> {
            cityLocation = titleLocalized.enLocale
            address = addressLocalized.enLocale
        }
        AM -> {
            cityLocation = titleLocalized.amLocale
            address = addressLocalized.amLocale
        }
        RU -> {
            cityLocation = titleLocalized.ruLocale
            address = addressLocalized.ruLocale
        }
    }

    return com.example.exchangerate.entity.rates.OrganizationParams(
        organizationId = organizationId,
        organizationName = organizationName,
        organizationCityLocation = cityLocation,
        organizationAddress = address,
        organizationContacts = contacts,
        workingHours = workHours.map { Pair(it.days, it.hours) },
        organizationLocation = Pair(location.lat, location.lng)
    )
}
