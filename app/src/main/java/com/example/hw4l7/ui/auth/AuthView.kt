package com.example.hw4l7.ui.auth

import com.google.firebase.auth.FirebaseUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateUI(currentUser: FirebaseUser?)
}