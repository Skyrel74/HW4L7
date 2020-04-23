package com.example.hw4l7.ui

import com.example.hw4l7.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(products: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addItem(position: Int)
}