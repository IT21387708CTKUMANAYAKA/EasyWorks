package com.example.easyworks.activities




import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.easyworks.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var btnInsertReview: Button
    private lateinit var btnFetchReview: Button




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()


        btnInsertReview = findViewById(R.id.btnInsertReview)
        btnFetchReview = findViewById(R.id.btnFetchReview)

        btnInsertReview.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }

        btnFetchReview.setOnClickListener {
            val intent = Intent(this, FetchingActivity1::class.java)
            startActivity(intent)
        }



    }
}