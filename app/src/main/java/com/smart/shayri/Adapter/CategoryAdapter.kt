package com.smart.shayri.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smart.shayri.AllShayriActivity
import com.smart.shayri.MainActivity
import com.smart.shayri.Model.CategoryModel
import com.smart.shayri.R
import com.smart.shayri.databinding.ItemCategoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewholder>() {

    val colorList = arrayListOf<String>("#1abc9c", "#9b59b6", "#2ecc71", "#3498db", "#e67e22")

    class CatViewholder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewholder {
        return CatViewholder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewholder, position: Int) {
//        holder.binding.itemText.text = list[position].toString()

        if (position % 5 == 0) {
            holder.binding.itemText.setBackgroundResource(R.drawable.gradient_1)
        }
        else if (position % 5 == 1) {
            holder.binding.itemText.setBackgroundResource(R.drawable.gradient_2)
        }
        else if (position % 5 == 2) {
            holder.binding.itemText.setBackgroundResource(R.drawable.gradient_3)
        }
        else if (position % 5 == 3) {
            holder.binding.itemText.setBackgroundResource(R.drawable.gradient_4)
        }
        else if (position % 5 == 4) {
            holder.binding.itemText.setBackgroundResource(R.drawable.gradient_5)
        }

        holder.binding.itemText.text = list[position].name.toString()

        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity,AllShayriActivity::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name.toString())
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}