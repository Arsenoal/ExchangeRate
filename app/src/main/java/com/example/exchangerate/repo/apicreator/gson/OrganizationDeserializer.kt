package com.example.exchangerate.repo.apicreator.gson

import com.example.exchangerate.repo.rates.model.*
import com.google.gson.*
import java.lang.reflect.Type

class OrganizationDeserializer: JsonDeserializer<KeyValueResponse<String, OrganizationParams>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): KeyValueResponse<String, OrganizationParams> {
        val gson = Gson()

        val response = gson.fromJson(json, KeyValueResponse<String, OrganizationParams>().javaClass)

        val map: MutableMap<String, OrganizationParams> = mutableMapOf()
        json?.run {
            val jsonObject = asJsonObject

            val listObject = jsonObject.getAsJsonObject("list")

            listObject.keySet().forEach { key ->
                val obj = listObject.getAsJsonObject(key)

                val title = obj.getAsJsonObject("title")
                val address = obj.getAsJsonObject("address")
                val location = obj.getAsJsonObject("location")

                map[key] = OrganizationParams(
                    head = obj.get("head").asInt,
                    titleLocalized = LocalizedModel(
                        enLocale = title.get("en").asString,
                        amLocale = title.get("am").asString,
                        ruLocale = title.get("ru").asString
                    ),
                    addressLocalized = LocalizedModel(
                        enLocale = address.get("en").asString,
                        amLocale = address.get("am").asString,
                        ruLocale = address.get("ru").asString
                    ),
                    location = Location(
                        lat = location.get("lat").asString,
                        lng = location.get("lng").asString
                    ),
                    contacts = obj.get("contacts").asString,
                    workHours = obj.get("workhours").asJsonArray.map { workHourJsonElement ->
                        val workHourObj = workHourJsonElement as JsonObject
                        WorkHours(
                            days = workHourObj.get("days").asString,
                            hours = workHourObj.get("hours").asString
                        )
                    }
                )
            }
        }

        response.data = map
        return response
    }
}