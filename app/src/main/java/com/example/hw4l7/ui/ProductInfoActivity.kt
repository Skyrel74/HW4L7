package com.example.hw4l7.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.hw4l7.R
import kotlinx.android.synthetic.main.product_info_layout.*

class ProductInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_info_layout)

        setSupportActionBar(productToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }
}