package com.example.hw4l7.domain

import com.example.hw4l7.domain.model.Cart

interface ViewedProductDao {

    /**
     * save this product as viewed
     * */
    fun addProduct(product: Cart)

    /**
     * get all viewed products
     * might be empty
     * */
    fun getAllProducts(): List<Cart>
}