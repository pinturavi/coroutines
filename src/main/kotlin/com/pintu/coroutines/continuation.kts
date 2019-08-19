package com.pintu.coroutines

val seq = sequence {

    println("one")
    yield(1)

    println("two")
    yield(2)

    println("three")
    yield(3)

    println("done")
}


for(value in seq){
    println("value $value")
}