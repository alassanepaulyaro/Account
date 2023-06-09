package com.example.ca_compte.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca_compte.data.model.Banks
import com.example.ca_compte.useCase.BankUseCase
import com.example.ca_compte.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val bankUseCase: BankUseCase
) : ViewModel() {

    private val _bankData = MutableLiveData<Resource<Banks>>()
    var bankData: LiveData<Resource<Banks>> = _bankData

    fun getBankData() {
        _bankData.postValue(Resource.Loading())
        viewModelScope.launch {
            _bankData.value = bankUseCase.invoke()
        }
    }
}