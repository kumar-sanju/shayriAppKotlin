package com.smart.shayri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.smart.shayri.Adapter.ShayriListAdapter
import com.smart.shayri.Model.CategoryListModel
import com.smart.shayri.databinding.ActivityAllShayriBinding

class AllShayriActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayriBinding
    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllShayriBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_all_shayri)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.toolbarName.text = name.toString()

        db.collection("Shayri").document(id!!).collection("all")
            .addSnapshotListener { value, error ->
            val shayriList = arrayListOf<CategoryListModel>()
            val data = value?.toObjects(CategoryListModel::class.java)
            shayriList.addAll(data!!)

            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = ShayriListAdapter(this,shayriList)

        }

    }
}