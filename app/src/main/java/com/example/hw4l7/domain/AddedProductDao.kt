package com.example.hw4l7.domain

interface AddedProductDao {

    /**
     * save this product as viewed
     * */
    fun addProduct(product: RemoteProduct)

    /**
     * get all viewed products
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

    /**
     * Getter of size
     */
    fun size(): Int
}