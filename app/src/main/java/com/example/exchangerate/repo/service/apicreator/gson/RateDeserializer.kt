package com.example.exchangerate.repo.service.apicreator.gson

import com.example.exchangerate.repo.commonmodels.BuySell
import com.example.exchangerate.repo.commonmodels.Currency
import com.example.exchangerate.repo.commonmodels.Rate
import com.example.exchangerate.repo.commonmodels.KeyValueResponse
import com.google.gson.*
import java.lang.reflect.Type

class RateDeserializer: JsonDeserializer<KeyValueResponse<String, Rate>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): KeyValueResponse<String, Rate> {
        val gson = Gson()

        val response = gson.fromJson(json, KeyValueResponse<String, Rate>().javaClass)

        val map: MutableMap<String, Rate> = mutableMapOf()
        json?.run {
            val jsonObject = asJsonObject

            jsonObject.keySet().forEach {
                val element = jsonObject.getAsJsonObject(it)

                val currencyMap = mutableMapOf<String, Currency>()

                val listCurrencyObject = element.getAsJsonObject("list")

                listCurrencyObject.keySet().forEach { key ->
                    val obj = listCurrencyObject.getAsJsonObject(key)
                    currencyMap[key] = Currency(
                        zero = BuySell(
                            buy = obj.get("0")?.run{ asJsonObject.get("buy").asString },
                            sell = obj.get("0")?.run{ asJsonObject.get("sell").asString }
                        ),
                        one = BuySell(
                            buy = obj.get("1")?.run{ asJsonObject.get("buy").asString },
                            sell = obj.get("1")?.run{ asJsonObject.get("sell").asString }
                        )
                    )
                }

                map[it] = Rate(
                    title = element.get("title").asString,
                    date = element.get("date").asString,
                    logo = element.get("logo").asString,
                    listCurrency = currencyMap
                )
            }
        }

        response.data = map
        return response
    }
}