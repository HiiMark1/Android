package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var secondClass: SecondClass = SecondClass()
        var thirdClass: ThirdClass = ThirdClass()

        println("Вызов метода второго класса ")
        secondClass.interfaceForSecondClassMethod()

        println("Вызов метода третьего класса ")
        thirdClass.interfaceForThirdClassMethod()
        setContentView(R.layout.activity_main)
    }
}