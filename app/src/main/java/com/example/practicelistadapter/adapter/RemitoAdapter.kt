package com.example.practicelistadapter.adapter


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practicelistadapter.data.remito.get.Remito
import com.example.practicelistadapter.databinding.ItemRecyclerviewBinding

class RemitoAdapter() : RecyclerView.Adapter<RemitoAdapter.MyViewHolder>() {

    var hashMap: HashMap<String, String> = HashMap()
    var checkBoxStatesArray = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val remito = remitos[position]
        holder.binding.checkBoxAll.isChecked = checkBoxStatesArray.get(position, false)

        holder.binding.apply {
            tvCantidadRemito.text = "Cantidad: ${remito.cantidad}"
            tvFechaRemito.text = "Fecha: ${remito.fecha}"
            tvNumeroRemito.text = "Numero: ${remito.numero}"
        }
    }

    override fun getItemCount(): Int = remitos.size


    private val diffCallBack = object : DiffUtil.ItemCallback<Remito>(){
        override fun areItemsTheSame(oldItem: Remito, newItem: Remito): Boolean {
            return oldItem.codigo == newItem.codigo
        }

        override fun areContentsTheSame(oldItem: Remito, newItem: Remito): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var remitos: List<Remito>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }


    inner class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){
        var checkbox = binding.checkBoxAll

        init {
            checkbox.setOnClickListener {
                if(!checkBoxStatesArray.get(adapterPosition, false)){
                    checkbox.isChecked = true
                    checkBoxStatesArray.put(adapterPosition, true)
                    //When is clicked put that value in a HashMap {position : code} of remito
                    if(checkBoxStatesArray.get(adapterPosition)) {
                        hashMap[adapterPosition.toString()] = remitos[adapterPosition].codigo
                    }
                }else{
                    checkbox.isChecked = false
                    checkBoxStatesArray.put(adapterPosition, false)
                    //If isn't clicked remove that position of HashMap
                    hashMap.remove(adapterPosition.toString())
                }
            }
        }
    }



}

