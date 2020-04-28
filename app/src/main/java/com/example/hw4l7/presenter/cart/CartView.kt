package com.example.hw4l7.presenter.cart

import com.example.hw4l7.domain.model.Cart
import com.example.hw4l7.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: List<Cart>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProductDerailed(product: Cart)
}