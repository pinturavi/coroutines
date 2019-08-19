package com.pintu.coroutines

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.time.Duration
import java.time.Instant


data class ExchangeKt(
    var rates: Map<String, Double>,
    var base: String,
    var date: String
)


val BASE = "USD"

fun main() {
    try {
        val start = Instant.now()
        val symbols = getSymbols()
        val deferredRates = symbols.map { GlobalScope.async { getExchangeRates(it) } }
        GlobalScope.launch {
            deferredRates.forEach { println(it.await()) }
        }
        println(Duration.between(start, Instant.now()).toMillis())
        Thread.sleep(5000)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun getSymbols(): Sequence<String> {
    return sequenceOf(
        "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY","INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY","INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
        "SGD", "JPY", "INR", "CAD", "SGD", "JPY"
    )
}


fun getExchangeRates(symbol: String): Double? {
    val data: String
    return try {
        data = getExchangeRate(symbol)
        val gson = Gson()
        gson.fromJson(data, ExchangeKt::class.java).rates[symbol]
    } catch (e: IOException) {
        e.printStackTrace()
        0.0
    }

}

@Throws(IOException::class)
fun getExchangeRate(symbol: String): String {
    val url = URL("https://api.exchangeratesapi.io/latest?base=$BASE&symbols=$symbol")
    val reader = BufferedReader(InputStreamReader(url.openStream()))
    return reader.lines().findFirst().get()
}

