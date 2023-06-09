package com.example.ca_compte.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("banks") val banks: List<Bank>
)