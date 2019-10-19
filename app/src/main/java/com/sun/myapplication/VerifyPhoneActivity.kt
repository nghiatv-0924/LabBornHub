package com.sun.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verify_phone.*
import java.util.concurrent.TimeUnit

class VerifyPhoneActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)
        val phone = intent.getStringExtra("phone")
        phone?.let {
            sendVerificationCode(phone)
        }
    }

    private fun sendVerificationCode(phone: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phone.addCountryCode(),
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        )
    }

    private fun String.addCountryCode() = let {
        VIET_NAME_COUNTRY_CODE + it.substring(1)
    }

    private val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            val code = phoneAuthCredential.smsCode
            if (code != null) {
                pinView.setText(code)
                verifyVerificationCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@VerifyPhoneActivity, e.message, Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(id, token)
            verificationId = id
            resendToken = token
        }
    }

    private fun verifyVerificationCode(otp: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(
                        this,
                        HomeActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    var message = "Somthing is wrong, we will fix it soon..."
                    if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }
                    val snackbar =
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
    }

    companion object {
        private const val VIET_NAME_COUNTRY_CODE = "+84"
    }
}
