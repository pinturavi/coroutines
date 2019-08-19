package com.pintu.coroutines

import com.google.gson.Gson
import java.io.IOException
import java.net.URL

data class ExchangeKt(
    var rates: Map<String, Double>,
    var base: String,
    var date: String
)

val symbols = sequenceOf(
    "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
    "SGD", "JPY", "INR", "CAD", "SGD", "JPY"
)


fun getExchangeRateFromJson(data: String, symbol: String): Double? {
    return try {
        val gson = Gson()
        gson.fromJson(data, ExchangeKt::class.java).rates[symbol]
    } catch (e: IOException) {
        e.printStackTrace()
        0.0
    }

}

@Throws(IOException::class)
fun getExchangeRate(symbol: String) =
    URL("https://api.exchangeratesapi.io/latest?base=$BASE&symbols=$symbol").readText()


fun measureTime(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("Time difference ${(end - start) / 1.0e9}")
}



measureTime {
    val exchangeValues = mutableListOf<Double?>()
    for (symbol in symbols) {
        exchangeValues += getExchangeRateFromJson(getExchangeRate(symbol), symbol)
    }

    for (exchange in exchangeValues) {
        println(exchange)
    }


}

Thread.sleep(1000)