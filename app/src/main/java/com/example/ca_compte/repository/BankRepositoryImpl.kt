package com.example.ca_compte.repository

import androidx.annotation.WorkerThread
import com.example.ca_compte.data.BankAPI
import com.example.ca_compte.data.model.Banks
import com.example.ca_compte.utils.Resource
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(private val bankAPI: BankAPI) : BankRepository {
    @WorkerThread
    override suspend fun getBankData(): Resource<Banks> {
        return try {
            val response = bankAPI.getBankData()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Error", null)
            } else {
                Resource.Error("Error No data!", null)

            }
        } catch (e: Exception) {
            Resource.Error("Couldn't connect to  the servers. Check your internet connection", null)
        }
    }
}