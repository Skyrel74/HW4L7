package com.example.hw4l7.domain

interface AddedProductDao {

    /**
     * save this product as added to cart
     * */
    fun addProduct(product: RemoteProduct)

    /**
     * get all products from cart
     * might be empty
     * */
    fun getAllProducts(): List<RemoteProduct>

    /**
     * Clear list of products after buying
     */
    fun clearCart()

    /**
     * Remover product from cart by position
     */
    fun remove(product: RemoteProduct)
}