package com.example.hw4l7.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hw4l7.R
import com.example.hw4l7.presenter.signup.SignUpPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_signup.*
import moxy.ktx.moxyPresenter

class SignUpActivity : BaseActivity(), SignUpView {

    private val presenter by moxyPresenter { SignUpPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        SignUpButton.setOnClickListener {
            presenter.signUpUser()
        }
    }

    override fun checkForm() {
        val email = SignUpEmail.text.toString()
        val password = SignUpPassword.text.toString()

        if (email.isEmpty()) {
            SignUpEmail.error = "Please enter email"
            SignUpEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpEmail.error = "Please enter valid email"
            SignUpEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            SignUpPassword.error = "Please enter password"
            SignUpPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            SignUpPassword.error = "Password must be at least 6 characters"
            SignUpPassword.requestFocus()
            return
        }
        presenter.createAccount(SignUpEmail.text.toString(), SignUpPassword.text.toString())
    }

    override fun showSignUpError() =
        Toast.makeText(
            this, "Sign Up failed. Try again after some time.",
            Toast.LENGTH_SHORT
        ).show()

    override fun finishSignUp() {
        startActivity(
            Intent(this, AuthActivity::class.java)
                .addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
        )
        finish()
    }
}