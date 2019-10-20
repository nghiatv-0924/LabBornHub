package com.sun.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.sun.myapplication.ui.home.HomeActivity
import com.sun.myapplication.R
import com.sun.myapplication.ui.verify.VerifyPhoneActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonContinue.setOnClickListener {
            val phone = editTextPhone.text.toString().trim()
            if (phone.isEmpty() || phone.length < 10) {
                editTextPhone.error = "Enter a valid mobile"
                editTextPhone.requestFocus()
            } else {
                val intent = Intent(this, VerifyPhoneActivity::class.java)
                intent.putExtra("phone", phone)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}
