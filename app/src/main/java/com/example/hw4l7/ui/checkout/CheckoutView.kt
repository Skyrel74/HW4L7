package com.example.hw4l7.ui.checkout

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CheckoutView : MvpView {

    /**
     * Show error for EditText by bool value
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForName(isVisible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForSurname(isVisible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForSecondName(isVisible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhone(isVisible: Boolean)
}