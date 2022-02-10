package com.example.sslpinning

import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("title")
    val title: String
)
