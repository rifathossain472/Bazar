package com.esports.bazar.view.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.esports.bazar.data.models.AuthService

class RegistrationViewModel : ViewModel() {

    fun userRegistration(user: User){
        val authService = AuthService()
        authService.userRegistration(user).addOnSuccessListener {
            Log.d("TAG", "userRegistration: Success")
        }.addOnFailureListener{
            Log.d("TAG", "userRegistration: ${it.message}")
        }
    }


}