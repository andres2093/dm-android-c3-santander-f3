package com.bedu.auth.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bedu.auth.databinding.ActivityOptionsBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.CustomKeysAndValues
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.lang.Exception

class MainActivity : Activity() {

    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FirebaseApp.initializeApp(this)

        handleClick()
    }

    override fun onStart() {
        super.onStart()

    }

    private fun handleClick() {

        binding.btnEmail.setOnClickListener {
            val intent = Intent(this, EmailActivity::class.java)
            startActivity(intent)
        }
        binding.btnPhone.setOnClickListener {
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
        }
        binding.btnGoogle.setOnClickListener {
        }
        binding.btnCrash.setOnClickListener {
            Toast.makeText(this, "Crash!", Toast.LENGTH_SHORT).show()
            throw RuntimeException("Test Crash") // Force a crash
        }
        binding.btnInfo.setOnClickListener {
            Toast.makeText(this, "setInfo!", Toast.LENGTH_SHORT).show()
            FirebaseCrashlytics.getInstance().setCustomKey("str_key", "Bedu")
            FirebaseCrashlytics.getInstance().setCustomKey("bool_key", true)
            FirebaseCrashlytics.getInstance().setCustomKey("int_key", 15)
            FirebaseCrashlytics.getInstance().setCustomKey("long_key", 15L)
            FirebaseCrashlytics.getInstance().setCustomKey("float_key", 1.0f)
            FirebaseCrashlytics.getInstance().setCustomKey("double_key", 1.0)

            FirebaseCrashlytics.getInstance().setCustomKeys(CustomKeysAndValues.Builder()
                .putString("str_key", "Beto")
                .putInt("int_key_1", 16)
                .build())

            FirebaseCrashlytics.getInstance().log("Estoy dentro del btnInfo")
            FirebaseCrashlytics.getInstance().log("Higgs-Boson detected! Bailing out")

            FirebaseCrashlytics.getInstance().setUserId("123")

            try {
                Log.e(TAG, "handleClick: " + 0 / 0)
            } catch (e: Exception){
                FirebaseCrashlytics.getInstance().recordException(e)
            }

//            throw RuntimeException("Test Crash") // Force a crash
        }
    }



    private fun updateUI() {

    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

}