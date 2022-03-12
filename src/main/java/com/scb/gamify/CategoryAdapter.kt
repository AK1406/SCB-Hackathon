package com.scb.gamify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    var context: Context,
    private var arrayList: ArrayList<CategoryModel>,
    private val onItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return MyViewHolder(viewHolder, onItemClick)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindData(arrayList[position])

    }

    inner class MyViewHolder(itemView: View, val onItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private var categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)!!
        private var categoryName = itemView.findViewById<TextView>(R.id.categoryName)!!

        fun bindData(category: CategoryModel) {
            categoryImage.setImageResource(category.image)
            categoryName.text = category.name
            itemView.setOnClickListener {
                onItemClick(category.id)
            }

        }
    }

}