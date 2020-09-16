package com.example.hw4l7.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hw4l7.R
import com.example.hw4l7.presenter.signup.SignUpPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.auth.AuthActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import moxy.ktx.moxyPresenter

class SignUpActivity : BaseActivity(), SignUpView {

    private lateinit var auth: FirebaseAuth

    private val presenter by moxyPresenter { SignUpPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        SignUpButton.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (SignUpEmail.text.toString().isEmpty()) {
            SignUpEmail.error = "Please enter email"
            SignUpEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(SignUpEmail.text.toString()).matches()) {
            SignUpEmail.error = "Please enter valid email"
            SignUpEmail.requestFocus()
            return
        }

        if (SignUpPassword.text.toString().isEmpty()) {
            SignUpPassword.error = "Please enter password"
            SignUpPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(
            SignUpEmail.text.toString(),
            SignUpPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, AuthActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}