package com.example.hw4l7.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.hw4l7.R
import kotlinx.android.synthetic.main.catalog_layout.*


class CatalogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        productInfoBtn.setOnClickListener {
            startActivity(Intent(this, ProductInfoActivity::class.java))
        }

        cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        Log.d(tag, "savedInstanceState = $savedInstanceState")
        val savedInt = savedInstanceState?.getInt(SAVE_SATE_INT)
        Log.d(tag, "savedInt $savedInt")

        catalogCheckoutbtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java).apply {
                putExtra(PRODUCT_ID, 1000)
            }
            startActivityForResult(intent, REQUEST_AUTH)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_SATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_AUTH == requestCode) {
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, "onActivityResult ${isUserAuth.toString()}")
        }
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_SATE_INT = "SAVE_SATE_INT"
    }
}