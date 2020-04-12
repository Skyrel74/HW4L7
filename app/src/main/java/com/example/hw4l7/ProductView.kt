package com.example.hw4l7

interface ProductView: PriceFormatter {
    /**
     * Outputs price in <PRICE>P format or <NAME>: <PRICE>P format.
     */
    fun print(price: Double)
    fun print(name: String, price: Double)

    /**
     * Create payment bill in TableView
     */
    fun print(products: List<Product>)
}