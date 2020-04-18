package com.example.hw4l7.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.hw4l7.R
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        cartBackBtn.setOnClickListener {
            finish()
            Log.d(tag, "finished")
        }
    }
}