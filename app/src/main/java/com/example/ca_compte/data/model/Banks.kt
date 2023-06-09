package com.example.ca_compte.data.model

import com.google.gson.annotations.SerializedName

data class Banks(
    @SerializedName("banks") val banks: List<Bank>
)