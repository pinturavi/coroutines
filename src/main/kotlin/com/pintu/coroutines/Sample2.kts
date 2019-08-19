package com.pintu.coroutines

fun greet(msg:String="hi", name:String) = println("$msg $name")


greet("hello", "bob")
greet(name="bob")
