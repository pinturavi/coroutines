package com.pintu.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun process(n:Int):Int{
    println("Process ${Thread.currentThread()}")
    return  n
}

println(Thread.currentThread())

GlobalScope.launch {
    process(2)
}