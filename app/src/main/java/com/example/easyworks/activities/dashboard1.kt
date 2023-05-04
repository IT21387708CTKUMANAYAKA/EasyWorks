package com.example.easyworks.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.example.easyworkscrud.R

class dashboard1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard1)

        val joinusButton = findViewById<Button>(R.id.dashjoinus)
        joinusButton.setOnClickListener {
            val regi = Intent(this, Login::class.java)
            startActivity(regi)
        }
    }
}