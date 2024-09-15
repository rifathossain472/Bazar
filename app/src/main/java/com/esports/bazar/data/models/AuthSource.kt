package com.esports.bazar.data.models

import com.esports.bazar.view.register.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthSource {
    fun userRegistration(user: User): Task<AuthResult>

    fun userLogin(user: User)
}