package com.example.hw4l7.ui.cart

import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {

    /**
     * Show all products from cart
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<RemoteProduct>)

    /**
     * Remove item from cart
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(product: RemoteProduct)
}