package com.example.submission_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Smartphone(
    val name: String,
    val price: String,
    val desc: String,
    val specification: String,
    val image: Int,
    val link: String
): Parcelable
