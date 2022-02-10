package com.example.sslpinning

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    val message: String,
)
