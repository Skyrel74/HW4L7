package com.example.hw4l7.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hw4l7.R
import com.example.hw4l7.presenter.auth.AuthPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.catalog.CatalogActivity
import com.example.hw4l7.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_auth.*
import moxy.ktx.moxyPresenter

class AuthActivity : BaseActivity(), AuthView {

    private lateinit var auth: FirebaseAuth

    private val presenter by moxyPresenter { AuthPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auth)
        auth = FirebaseAuth.getInstance()

        LogInButton.setOnClickListener {
            startActivity(Intent(this, CatalogActivity::class.java))
            finish()
        }

        SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun doLogin() {
        if (Email.text.toString().isEmpty()) {
            Email.error = "Please enter email"
            Email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Email.error = "Please enter valid email"
            Email.requestFocus()
            return
        }

        if (Password.text.toString().isEmpty()) {
            Password.error = "Please enter password"
            Password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(Email.text.toString(), Password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, CatalogActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}