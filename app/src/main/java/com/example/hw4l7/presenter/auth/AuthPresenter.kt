package com.example.hw4l7.presenter.auth

import android.util.Log
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.auth.AuthView
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import java.net.ConnectException
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor() : BasePresenter<AuthView>() {

    private lateinit var auth: FirebaseAuth

    fun doLogin() {
        auth = FirebaseAuth.getInstance()
        viewState.checkForm()
    }

    fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewState.moveToCatalog()
                } else
                    viewState.showLogInError()
            }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            Log.d("Exception in Auth: ", e.toString())
    }
}