package com.sun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContinue.setOnClickListener {
            val mobile = editTextMobile.text.toString().trim()
            if (mobile.isEmpty()) {
                editTextMobile.error = "Enter a valid mobile"
                editTextMobile.requestFocus()
            } else {
                val intent =  Intent (this, VerifyPhoneActivity::class.java)
                intent.putExtra("mobile", mobile)
                startActivity(intent)
            }
        }
    }
}
