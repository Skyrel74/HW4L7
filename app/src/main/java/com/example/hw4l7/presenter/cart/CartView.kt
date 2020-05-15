package com.example.hw4l7.presenter.cart

import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<RemoteProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(product: RemoteProduct)
}