package com.example.practicelistadapter

import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.forEach
import androidx.core.util.size
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicelistadapter.data.Remito
import com.example.practicelistadapter.data.RemitoResponse
import com.example.practicelistadapter.databinding.FragmentRemitoBinding
import com.example.practicelistadapter.databinding.ItemRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sign

@AndroidEntryPoint
class RemitoFragment : Fragment(){

    private val remitoViewModel: RemitoViewModel by viewModels()
    private lateinit var binding: FragmentRemitoBinding
    private lateinit var itemRemitoBinding : ItemRecyclerviewBinding
    private lateinit var remitoAdapter: RemitoAdapter
    var hashMap: HashMap<Int, Boolean> = HashMap()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRemitoBinding.inflate(layoutInflater, container,false)
        val myItemView = layoutInflater.inflate(R.layout.item_recyclerview, null)
        itemRemitoBinding = ItemRecyclerviewBinding.inflate(layoutInflater, myItemView as ViewGroup, false)
        setUpRecyclerView()




        return binding.root
    }

    private fun setUpRecyclerView() {
        remitoAdapter = RemitoAdapter()
        binding.rvFilterByRemito.apply {
            adapter = remitoAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        remitoViewModel.responseRemito.observe(viewLifecycleOwner, {
            remitoAdapter.remitos = it
            Log.d(TAG, remitoAdapter.checkBoxStatesArray.toString())

            binding.btnPostDetailsRemitos.setOnClickListener {
                remitoAdapter.checkBoxStatesArray.forEach{ i: Int, b: Boolean ->
                    if(b){
                        hashMap[i] = b
                    }
                }
                Log.d(TAG, "Mine array1 $hashMap")
                hashMap.clear()
                Log.d(TAG, "Mine array2 $hashMap")
                Log.d(TAG, "Array of adapter ${remitoAdapter.checkBoxStatesArray}")
            }
        })
    }

    companion object{
        const val TAG = "RemitoFragment"
    }

}

