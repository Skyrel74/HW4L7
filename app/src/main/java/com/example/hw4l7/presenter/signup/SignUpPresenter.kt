package com.example.hw4l7.presenter.signup

import android.util.Log
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.signup.SignUpView
import moxy.InjectViewState
import java.net.ConnectException
import javax.inject.Inject

@InjectViewState
class SignUpPresenter @Inject constructor() : BasePresenter<SignUpView>() {

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            Log.d("Exception in Sign Up: ", e.toString())
    }
}