package com.pintu.coroutines

import java.math.BigInteger

private val Int.BI: BigInteger
    get() {
        return this.toBigInteger()
    }

fun factorial(number: BigInteger):BigInteger =
    if (number < 2.BI)
        number
    else factorial(number - 1.BI) * number

println(factorial(1000.BI))//will work

println(factorial(10000.BI))//will throw stack overflow exception
