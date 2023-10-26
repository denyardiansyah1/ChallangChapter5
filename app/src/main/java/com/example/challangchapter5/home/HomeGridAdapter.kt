package com.example.challangchapter5.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangchapter5.databinding.ItemMenuGridBinding
import com.example.challangchapter5.model.DataMenu

class HomeGridAdapter (var listMenu: List<DataMenu>, var onItemClick: ((DataMenu) -> Unit)): RecyclerView.Adapter<HomeGridAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemMenuGridBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMenuGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamaMakanan.text = listMenu[position].nama
        holder.binding.tvPriceFood.text = listMenu[position].harga.toString()
        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenu)
        holder.binding.cvItemCard.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}