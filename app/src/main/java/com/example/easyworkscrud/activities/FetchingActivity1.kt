package com.example.easyworkscrud.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.easyworkscrud.R
import com.example.easyworkscrud.adapters.CusAdapter
import com.example.easyworkscrud.models.CustomerModel
import com.google.firebase.database.*

class FetchingActivity1 : AppCompatActivity() {

    private lateinit var cusRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var cusList: ArrayList<CustomerModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        cusRecyclerView = findViewById(R.id.rvCus)
        cusRecyclerView.layoutManager = LinearLayoutManager(this)
        cusRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        cusList = arrayListOf<CustomerModel>()

        getCustomersData()

    }

    private fun getCustomersData() {

        cusRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Customers")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cusList.clear()
                if (snapshot.exists()){
                    for (cusSnap in snapshot.children){
                        val cusData = cusSnap.getValue(CustomerModel::class.java)
                        cusList.add(cusData!!)
                    }
                    val mAdapter = CusAdapter(cusList)
                    cusRecyclerView.adapter = mAdapter

                mAdapter.setOnItemClickListener(object : CusAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@FetchingActivity1, CustomerDetailsActivity::class.java)

                        //put extras
                        intent.putExtra("cusId",cusList[position].cusId)
                        intent.putExtra("cusName",cusList[position].cusName)
                        intent.putExtra("cusEmail",cusList[position].cusEmail)
                        intent.putExtra("cusReview",cusList[position].cusReview)
                        startActivity(intent)
                    }

                })


                    cusRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}