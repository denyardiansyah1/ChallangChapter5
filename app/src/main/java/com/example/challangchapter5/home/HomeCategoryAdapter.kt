package com.example.challangchapter5.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangchapter5.databinding.ItemCategoryBinding
import com.example.challangchapter5.model.DataCategory

class HomeCategoryAdapter (var listMenu: List<DataCategory>, var onItemClick: ((DataCategory) -> Unit)): RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamaMakanan.text = listMenu[position].nama

        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenu)
        holder.binding.cvItemCard.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}