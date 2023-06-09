package com.example.ca_compte.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
    @SerializedName("amount") val amount: String,
    @SerializedName("category") val category: String,
    @SerializedName("date") val date: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String
) : Parcelable