package com.example.ca_compte.di

import com.example.ca_compte.data.BankAPI
import com.example.ca_compte.repository.BankRepositoryImpl
import com.example.ca_compte.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun providerBankApi(): BankAPI = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        .create(BankAPI::class.java)

    @Singleton
    @Provides
    fun providerBankRepository(bankAPI: BankAPI) = BankRepositoryImpl(bankAPI)
}