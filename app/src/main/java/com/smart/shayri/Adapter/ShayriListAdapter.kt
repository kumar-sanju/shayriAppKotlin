package com.smart.shayri.Adapter

import android.content.*
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smart.shayri.AllShayriActivity
import com.smart.shayri.Model.CategoryListModel
import com.smart.shayri.databinding.ItemShayriBinding
import com.smart.shayri.BuildConfig


class ShayriListAdapter(val allShayriActivity: AllShayriActivity, val shayriList: ArrayList<CategoryListModel>) :
    RecyclerView.Adapter<ShayriListAdapter.ShayariViewHolder>() {

    val colorList = arrayListOf<String>("#1abc9c", "#9b59b6", "#2ecc71", "#3498db", "#e67e22")

    class ShayariViewHolder(val binding: ItemShayriBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {

        when {
            position % 5 == 0 -> {
                holder.binding.mainLinear.setBackgroundColor(Color.parseColor(colorList[0]))
            }
            position % 5 == 1 -> {
                holder.binding.mainLinear.setBackgroundColor(Color.parseColor(colorList[1]))
            }
            position % 5 == 2 -> {
                holder.binding.mainLinear.setBackgroundColor(Color.parseColor(colorList[2]))
            }
            position % 5 == 3 -> {
                holder.binding.mainLinear.setBackgroundColor(Color.parseColor(colorList[3]))
            }
            position % 5 == 4 -> {
                holder.binding.mainLinear.setBackgroundColor(Color.parseColor(colorList[4]))
            }
        }

        holder.binding.itemShayri.text = shayriList[position].data.toString()

        holder.binding.copyLayout.setOnClickListener {
            try {
                var clipboard: ClipboardManager? = allShayriActivity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                var clip = ClipData.newPlainText("label", shayriList[position].data.toString())
                clipboard?.setPrimaryClip(clip)
                Toast.makeText(allShayriActivity,"Shayri coppied successfully.", Toast.LENGTH_SHORT).show()
            } catch (ex: ActivityNotFoundException) {

            }
        }
        holder.binding.whatsappLayout.setOnClickListener {
            try {
                val whatsappIntent = Intent(Intent.ACTION_SEND)
                whatsappIntent.type = "text/plain"
                whatsappIntent.setPackage("com.whatsapp")
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayriList[position].data.toString())
                try {
                    allShayriActivity.startActivity(whatsappIntent)
                    Toast.makeText(allShayriActivity,"Shayri coppied successfully.", Toast.LENGTH_SHORT).show()
                } catch (ex: ActivityNotFoundException) {
                }
            } catch (ex: ActivityNotFoundException) {
            }

        }
        holder.binding.shareLayout.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${shayriList[position].data}\n\n"
                shareMessage = """${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                
                """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayriActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
                Toast.makeText(allShayriActivity,"Shayri coppied successfully.", Toast.LENGTH_SHORT).show()
            } catch (ex: ActivityNotFoundException) {
            }
        }

    }

    override fun getItemCount() = shayriList.size
}