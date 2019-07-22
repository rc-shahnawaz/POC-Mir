package com.example.myfirstandroidapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstandroidapp.model.APIResponse
import com.example.myfirstandroidapp.retrofit.API
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the Send button */
    fun submit(view: View) {
        // Do something in response to button

        val intent = Intent(this@MainActivity, TabActivity::class.java).apply {
            putExtra(EXTRA_CUSTOMER_ID, txtCID.text.toString())
            putExtra(EXTRA_LAST_NAME, txtLN.text.toString())
            putExtra(EXTRA_EMAIL, txtEM.text.toString())
            putExtra(EXTRA_FIRST_NAME, txtFN.text.toString())
            putExtra(EXTRA_ZIP_CODE, txtZIP.text.toString())
        }

        startActivity(intent)
    }

    companion object {
        @JvmStatic
        val EXTRA_CUSTOMER_ID = "customerID"
        @JvmStatic
        val EXTRA_LAST_NAME = "lastName"
        @JvmStatic
        val EXTRA_FIRST_NAME = "firstName"
        @JvmStatic
        val EXTRA_ZIP_CODE = "zipCode"
        @JvmStatic
        val EXTRA_EMAIL = "email"
    }
}
