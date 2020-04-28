package com.example.hw4l7.domain

import com.example.hw4l7.domain.model.Cart

interface ViewedProductDao {

    /**
     * save this product id as viewed
     * */
    fun addProduct(productId: Long)

    /**
     * get all viewed product ids
     * might be empty
     * */
    fun getAllProductIds(): List<Long>

    /**
     * get all viewed products
     * might be empty
     * */
    fun getAllProducts(): List<Cart>
}