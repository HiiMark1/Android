package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

class Song (
    val id: Int,
    @DrawableRes
    val photo: Int,
    @RawRes
    val music: Int,
    val name: String,
    val groupName: String,
    val description: String,
)