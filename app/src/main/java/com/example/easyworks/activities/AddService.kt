package com.example.easyworks.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.easyworks.models.ServiceModel
import com.example.easyworkscrud.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addService : AppCompatActivity() {

    private lateinit var serviceproname : EditText
    private lateinit var serviceprocontact : EditText
    private lateinit var serviceproemail : EditText
    private lateinit var servicename : EditText
    // private lateinit var servicecategory : Spinner
    private lateinit var Sprice : EditText

    // private lateinit var serviceimg : ImageView

    private lateinit var submitbtn : Button
    private lateinit var cancelbutton : Button
    // private lateinit var imageuploadbtn : Button

    private lateinit var dbref : DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_service)


        var notifypagebtn = findViewById<Button>(R.id.notifypagebtn)
        notifypagebtn.setOnClickListener({
            val intent = Intent(this, Service_Notification::class.java)
            startActivity(intent)
        })

        var homebtn = findViewById<ImageButton>(R.id.homenotselserv)
        homebtn.setOnClickListener({
            val intent = Intent(this, ViewServices::class.java)
            startActivity(intent)
        })



        serviceproname = findViewById(R.id.spname)
        serviceprocontact = findViewById(R.id.spcontact)
        serviceproemail = findViewById(R.id.spemail)
        servicename = findViewById(R.id.stitle)
        //servicecategory = findViewById(R.id.etservcat)
        Sprice = findViewById(R.id.servprice)

        submitbtn = findViewById(R.id.addservice)
        cancelbutton = findViewById(R.id.cancelservice)
        // imageuploadbtn = findViewById(R.id.imagebtn)

        // serviceimg = findViewById(R.id.imageupload)

        dbref = FirebaseDatabase.getInstance().getReference("Services")

        submitbtn.setOnClickListener{
            saveServicedata()
        }

        /*    imageuploadbtn.setOnClickListener(){
                pickImageGallery()
            }*/



    }


    private fun saveServicedata(){
        //getting values
        val title = servicename.text.toString()
        // val category = servicecategory.selectedItem.toString()
        val price = Sprice.text.toString()
        val name = serviceproname.text.toString()
        val phone = serviceprocontact.text.toString()
        val email = serviceproemail.text.toString()






        if(name.isEmpty()){
            servicename.error = "Please enter service name"
        }
        if(phone.isEmpty()){
            servicename.error = "Please enter service name"
        }
        if(email.isEmpty()){
            servicename.error = "Please enter service name"
        }

        if(title.isEmpty()){
            servicename.error = "Please enter service name"
        }
        /*   if (category.isEmpty()) {
               Toast.makeText(this, "Please select a category", Toast.LENGTH_LONG).show()
               return
           }*/
        if(price.isEmpty()){
            Sprice.error = "Please enter Price"
        }

        //set an unique id
        val serviceid = dbref.push().key!!

        var service = ServiceModel(serviceid,title,price,name,phone,email,)

        dbref.child(serviceid).setValue(service)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()
                servicename.text.clear()
                Sprice.text.clear()
                serviceproname.text.clear()
                serviceprocontact.text.clear()
                serviceproemail.text.clear()

            }.addOnFailureListener{
                Toast.makeText(this,"Error ${error(message = "Error")}", Toast.LENGTH_LONG).show()

            }



    }



}