package com.example.ca_compte.data

import com.example.ca_compte.data.model.Banks
import retrofit2.Response
import retrofit2.http.GET

interface BankAPI {
    @GET("testApi/banks.json")
    suspend fun getBankData(): Response<Banks>
}