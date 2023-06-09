package com.example.ca_compte.repository

import com.example.ca_compte.data.model.Banks
import com.example.ca_compte.utils.Resource

interface BankRepository {
    suspend fun getBankData() : Resource<Banks>
}