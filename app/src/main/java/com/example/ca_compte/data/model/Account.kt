package com.example.ca_compte.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    @SerializedName("balance") val balance: Double,
    @SerializedName("holder") val contract_number: String,
    @SerializedName("") val holder: String,
    @SerializedName("label") val id: String,
    @SerializedName("") val label: String,
    @SerializedName("operations") val operations: List<Operation>,
    @SerializedName("order") val order: Int,
    @SerializedName("product_code") val product_code: String,
    @SerializedName("role") val role: Int
) : Parcelable