package com.esports.bazar.data.models

import com.esports.bazar.view.register.User
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthService : AuthSource {
    override fun userRegistration(user: User): Task<AuthResult> {
        val mAuth = FirebaseAuth.getInstance()

        return mAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: User) {
        TODO("Not yet implemented")
    }

}