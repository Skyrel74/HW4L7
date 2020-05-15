package com.example.hw4l7.domain.interactor

import com.example.hw4l7.domain.AddedProductDao
import com.example.hw4l7.domain.RemoteProduct
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val addedProductDao: AddedProductDao
) {

    operator fun invoke(product: RemoteProduct) {
        addedProductDao.addProduct(product)
    }
}