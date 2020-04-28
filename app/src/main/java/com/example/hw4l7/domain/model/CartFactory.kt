package com.example.hw4l7.domain.model

class CartFactory {
    fun createCartProduct(
        id: Int,
        name: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): Cart {
        val product = Product(price, salePercent, name)
        return Cart(id, imageUrl, product)
    }

    fun createCartProduct(
        dataString: String
    ): Cart {
        val data: List<String> = dataString.split(",")
        val id = data[0].toInt()
        val imageUrl = data[1]
        val price = data[2].toDouble()
        val salePercent = data[3].toInt()
        val name = data[4]
        return Cart(id, imageUrl, Product(price, salePercent, name))
    }
}