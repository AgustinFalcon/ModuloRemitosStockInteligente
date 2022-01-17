package com.example.practicelistadapter.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicelistadapter.data.remito.post.EpcRemito
import com.example.practicelistadapter.databinding.ItemFilterEpcFragmentBinding


class FilterEpcAdapter(private val postRemito: List<EpcRemito>) : RecyclerView.Adapter<FilterEpcAdapter.FilterEpcViewHolder>(){

    inner class FilterEpcViewHolder(var binding: ItemFilterEpcFragmentBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterEpcViewHolder {
        return FilterEpcViewHolder(ItemFilterEpcFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilterEpcViewHolder, position: Int) {
        holder.binding.tvShowepc.text = postRemito[position].epc
        Log.d("FilterEpcAdapter", "Valor del recyclerView = ${postRemito[position].epc}")
    }

    override fun getItemCount(): Int = postRemito.size
}