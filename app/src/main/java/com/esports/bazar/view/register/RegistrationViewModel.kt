package com.esports.bazar.view.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esports.bazar.core.DataState
import com.esports.bazar.data.models.AuthService

class RegistrationViewModel : ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<User>>()

    val registrationResponse: MutableLiveData<DataState<User>> = _registrationResponse

    fun userRegistration(user: User){

        _registrationResponse.postValue(DataState.Loading())

        val authService = AuthService()
        authService.userRegistration(user).addOnSuccessListener {
            _registrationResponse.postValue(DataState.Success(user))
            Log.d("TAG", "userRegistration: Success")
        }.addOnFailureListener{
            _registrationResponse.postValue(DataState.Error(it.message))
            Log.d("TAG", "userRegistration: ${it.message}")
        }
    }


}