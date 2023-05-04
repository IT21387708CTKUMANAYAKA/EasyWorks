package com.example.easyworks.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

import com.example.easyworkscrud.R
import com.example.easyworkscrud.databinding.ProfilepageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ProfilepageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfilepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.logoutbutton.setOnClickListener{
            auth.signOut()

            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            finish()
        }

        binding.deletebutton.setOnClickListener{
            val user = Firebase.auth.currentUser
            user?.delete()?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Account Successfully Deleted !",Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Log.e("error: ", it.exception.toString())
                }
            }
        }

        val accountpro = findViewById<AppCompatButton>(R.id.accountsetting)
        accountpro.setOnClickListener {
            val accprofile = Intent(this, FetActivity1::class.java)
            startActivity(accprofile)
        }

        val accountnotifi = findViewById<AppCompatButton>(R.id.notificationbutton)
        accountnotifi.setOnClickListener {
            val notifiprofile = Intent(this, Notification::class.java)
            startActivity(notifiprofile)
        }
        val updatepass = findViewById<AppCompatButton>(R.id.updatebutton)
        updatepass.setOnClickListener {
            val intent = Intent(this, UpdateLoginpassword::class.java)
            startActivity(intent)
        }

        var homebtn = findViewById<ImageButton>(R.id.homenotselserv)
        homebtn.setOnClickListener({
            val intent = Intent(this, ViewServices::class.java)
            startActivity(intent)
        })

        var ratbtn = findViewById<ImageButton>(R.id.ratingnotselserv)
        ratbtn.setOnClickListener({
            val intent = Intent(this, FetchingAct::class.java)
            startActivity(intent)
        })

        var servicebtn = findViewById<ImageButton>(R.id.servicenotselbtn)
        servicebtn.setOnClickListener({
            val intent = Intent(this, addService::class.java)
            startActivity(intent)
        })

    }



}