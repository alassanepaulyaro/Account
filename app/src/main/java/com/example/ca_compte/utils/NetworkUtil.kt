package com.example.ca_compte.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem
import retrofit2.Response

class NetworkUtil {
    companion object{
        fun hasInternetConnection(context: Context): Boolean {
            val connectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                return when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
            else{
                connectivityManager.activeNetworkInfo?.run {
                    return when(type){
                        ConnectivityManager.TYPE_WIFI -> return true
                        ConnectivityManager.TYPE_MOBILE -> return true
                        ConnectivityManager.TYPE_ETHERNET -> return true
                        else -> false
                    }
                }
            }
            return false
        }
    }

   /* private fun handleBankDataResponse(response: Response<List<Bank>>): Resource<List<BankItem>>? {
        return when {
            response.message().toString().contains("timeout") -> {
                Resource.Error("Timeout")
            }
            response.code() == 402 -> {
                Resource.Error("API Key Limited.")
            }
            response.code() == 404 -> {
                Resource.Error("File not found.")
            }
            response.body().isNullOrEmpty() -> {
                return Resource.Error("Bank List not found.")
            }
            response.isSuccessful -> {
                val bankList = response.body()
                //Resource.Success(getListBankToDisplay(bankList!!))
            }
            else -> {
                Resource.Error(response.message())
            }
        }
    }*/
}