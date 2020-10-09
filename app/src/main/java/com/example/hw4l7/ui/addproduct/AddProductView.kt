package com.example.hw4l7.ui.addproduct

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AddProductView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFillError()

    @StateStrategyType(SkipStrategy::class)
    fun addProduct(name: String, price: Double, img: Bitmap)

    @StateStrategyType(SkipStrategy::class)
    fun successfulAdded()
}