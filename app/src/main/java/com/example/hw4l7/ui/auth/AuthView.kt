package com.example.hw4l7.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun checkForm()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLogInError()

    @StateStrategyType(SkipStrategy::class)
    fun moveToCatalog()
}