package com.example.practicelistadapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicelistadapter.R
import com.example.practicelistadapter.viewmodel.RemitoViewModel
import com.example.practicelistadapter.adapter.RemitoAdapter
import com.example.practicelistadapter.databinding.FragmentRemitoBinding
import com.example.practicelistadapter.databinding.ItemRecyclerviewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemitoFragment : Fragment(){

    private val remitoViewModel: RemitoViewModel by viewModels()
    private lateinit var binding: FragmentRemitoBinding
    private lateinit var itemRemitoBinding : ItemRecyclerviewBinding
    private lateinit var remitoAdapter: RemitoAdapter
    var hashMap: HashMap<Int, Boolean> = HashMap()
    var otroMap = HashMap<String, String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRemitoBinding.inflate(layoutInflater, container,false)
        val myItemView = layoutInflater.inflate(R.layout.item_recyclerview, null)
        itemRemitoBinding = ItemRecyclerviewBinding.inflate(layoutInflater, myItemView as ViewGroup, false)
        setUpRecyclerView()

        //Clicked button
        binding.btnPostDetailsRemitos.setOnClickListener {
            //Send the data selected
            remitoViewModel.sendData(remitoAdapter.hashMap)
            Log.d(TAG, "Datos enviados ${remitoAdapter.hashMap}")

            Snackbar.make(requireView(), "Datos enviados al servidor", Snackbar.LENGTH_SHORT).show()
            remitoAdapter.checkBoxStatesArray.forEach{ i: Int, b: Boolean ->
                if(b){
                    hashMap[i] = b
                }
            }
            Log.d(TAG, "Mine array1 $hashMap")
            hashMap.clear()
            Log.d(TAG, "Array of adapter ${remitoAdapter.checkBoxStatesArray}")
            Log.d(TAG, "Valor MYHASHMAP en RemitoFragment = ${remitoViewModel.myHashMap}")
        }

        remitoViewModel.postRemito.observe(viewLifecycleOwner, {
            Log.d(TAG, "Valor del live data observer = ${remitoViewModel.postRemito.value}")
        })

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
        })
    }

    companion object{
        const val TAG = "RemitoFragment"
    }

}

