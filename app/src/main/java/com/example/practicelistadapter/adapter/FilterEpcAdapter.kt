package com.example.practicelistadapter.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicelistadapter.databinding.ItemFilterEpcFragmentBinding


class FilterEpcAdapter(private val myHashMap: HashMap<String, String>) : RecyclerView.Adapter<FilterEpcAdapter.FilterEpcViewHolder>(){

    inner class FilterEpcViewHolder(var binding: ItemFilterEpcFragmentBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterEpcViewHolder {
        return FilterEpcViewHolder(ItemFilterEpcFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilterEpcViewHolder, position: Int) {
        holder.binding.tvShowepc.text = myHashMap[position.toString()]
    }

    override fun getItemCount(): Int = myHashMap.size
}