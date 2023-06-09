package com.example.ca_compte.data.model

import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("accounts") val accounts: List<Account>,
    @SerializedName("isCA") val isCA: Int,
    @SerializedName("name") val name: String
)