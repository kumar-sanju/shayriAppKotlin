package com.smart.shayri

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.smart.shayri.Adapter.CategoryAdapter
import com.smart.shayri.Model.CategoryModel
import com.smart.shayri.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("Shayri").addSnapshotListener { value, error ->
            val list = arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel :: class.java)    // value? = data should not be null
            list.addAll(data!!)   // data!! = data should not through null exception  or data should not be null

            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = CategoryAdapter(this,list)
        }

//        val list= arrayListOf<String>("Love Shayri","Romantic Shayri","Sad Shayri")
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = CategoryAdapter(this,list)

        binding.drawerBtn.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
            }
            else {
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.share->{
                    val intent= Intent()
                    intent.action=Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
                    intent.type="text/plain"
                    startActivity(Intent.createChooser(intent,"Share To:"))
                    true
                }
                R.id.more->{
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true
                }
                R.id.rate->{
                    val rateIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                    startActivity(rateIntent)
                    true
                }
                else -> false
            }
        }


    }

    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        }
        else {
            super.onBackPressed()
        }
    }

}