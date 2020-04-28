package com.example.hw4l7.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.hw4l7.domain.ViewedProductDao
import com.example.hw4l7.domain.model.Cart
import com.example.hw4l7.domain.model.CartFactory
import com.example.hw4l7.domain.model.Product

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : ViewedProductDao {

    private val factory = CartFactory()

    private var savedProductIds: List<Long>
        get() = sharedPreferences.getString(PRODUCT_TAG, null)
            ?.split(",")
            ?.mapNotNull { it.toLongOrNull() } ?: emptyList()
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, value.joinToString(","))
            }
        }

    private var savedProducts: List<Cart>
        get() = sharedPreferences.getString(PRODUCT_TAG, null)
            ?.split(",")
            ?.map { factory.createCartProduct(it) }
            ?: emptyList()
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, value.joinToString(","))
            }
        }

    override fun addProduct(productId: Long) {
        val productIds: List<Long> = savedProductIds
        val newProductIds = mutableListOf<Long>().apply {
            add(productId)
            addAll(productIds.filter { it != productId })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProductIds(): List<Long> {
        return savedProductIds
    }

    override fun getAllProducts(): List<Cart> {
        return savedProducts
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}