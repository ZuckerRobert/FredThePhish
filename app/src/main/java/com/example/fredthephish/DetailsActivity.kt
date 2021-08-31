package com.example.fredthephish

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val TEXT_KEY = "text"
        val LOG_TAG = DetailsActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        Log.d(LOG_TAG, "onCreate")


    }
}