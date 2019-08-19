package com.pintu.coroutines

import java.math.BigInteger

private val Int.BI: BigInteger
    get() {
        return this.toBigInteger()
    }

tailrec fun factorial(fact:BigInteger=1.BI, number:BigInteger):BigInteger =
    if (number < 2.BI)
        fact
    else
        factorial(fact * number, number - 1.BI)
println(factorial(number = 10000.BI))
