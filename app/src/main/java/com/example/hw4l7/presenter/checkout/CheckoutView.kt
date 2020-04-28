package com.example.hw4l7.presenter.checkout

import com.example.hw4l7.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CheckoutView : MvpView {
    /**
     * Outputs price in <PRICE>P format or <NAME>: <PRICE>P format.
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(price: Double)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(name: String, price: Double)

    /**
     * Create payment bill in TableView
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(products: List<Product>)

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