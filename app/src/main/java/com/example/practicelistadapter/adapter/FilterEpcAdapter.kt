package com.example.practicelistadapter.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicelistadapter.data.remito.get.Remito
import com.example.practicelistadapter.data.remito.post.EpcRemito
import com.example.practicelistadapter.databinding.ItemFilterEpcFragmentBinding


class FilterEpcAdapter(private val postRemito: List<EpcRemito>)
    : ListAdapter<EpcRemito, FilterEpcAdapter.FilterEpcViewHolder>(FilterEpcDiffCallback()){

    inner class FilterEpcViewHolder(var binding: ItemFilterEpcFragmentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(epcRemito: EpcRemito){
            binding.tvShowepc.text = epcRemito.epc
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterEpcViewHolder {
        return FilterEpcViewHolder(ItemFilterEpcFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilterEpcViewHolder, position: Int) {
        //holder.binding.tvShowepc.text = postRemito[position].epc
        val data = getItem(position)
        holder.bind(data)
        Log.d("FilterEpcAdapter", "Valor del recyclerView = ${postRemito[position].epc}")
    }

    override fun getItemId(position: Int): Long {
        val articolo = currentList[position]
        return articolo.epc.hashCode().toLong()
    }


    //override fun getItemCount(): Int = postRemito.size


    companion object {
        const val TAG = "FilterEpcAdapter"
    }




}


private class FilterEpcDiffCallback() : DiffUtil.ItemCallback<EpcRemito>(){
    override fun areItemsTheSame(oldItem: EpcRemito, newItem: EpcRemito): Boolean {
        return oldItem.epc == newItem.epc
    }

    override fun areContentsTheSame(oldItem: EpcRemito, newItem: EpcRemito): Boolean {
        return oldItem == newItem
    }

}