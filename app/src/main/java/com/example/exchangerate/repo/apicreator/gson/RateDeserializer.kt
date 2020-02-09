package com.example.exchangerate.repo.apicreator.gson

import com.example.exchangerate.repo.rates.model.BuySell
import com.example.exchangerate.repo.rates.model.Currency
import com.example.exchangerate.repo.rates.model.Rate
import com.example.exchangerate.repo.rates.model.KeyValueResponse
import com.google.gson.*
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken

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