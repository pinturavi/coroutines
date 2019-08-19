package com.pintu.coroutines

println("ok")

data class Student(val name:String, val age:Int)

val student1 = Student("Bob", 20)

val student2 = Student("Alice", 25)

val student3 = Student("Bob", 20)

if(student1 == student3){
    println("student1 and student3 are same")
}


val name = "bob"
val age = 34

val message = "$name you are $age years old"

