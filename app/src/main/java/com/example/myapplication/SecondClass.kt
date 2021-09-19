package com.example.myapplication

class SecondClass: FirstClass(), ForSecond {
    override fun interfaceForSecondClassMethod() {
        println(name)
    }
}