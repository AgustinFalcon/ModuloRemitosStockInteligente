package com.example.practicelistadapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    //Remito ViewModel
    private val remitoViewModel: RemitoViewModel by activityViewModels()
    //Adapter
    private lateinit var remitoAdapter: RemitoAdapter
    //Content the position send
    var hashMap: HashMap<Int, Boolean> = HashMap()
    //Binding
    private var _binding: FragmentRemitoBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemRemitoBinding : ItemRecyclerviewBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRemitoBinding.inflate(layoutInflater, container,false)
        val myItemView = layoutInflater.inflate(R.layout.item_recyclerview, null)
        itemRemitoBinding = ItemRecyclerviewBinding.inflate(layoutInflater, myItemView as ViewGroup, false)

        //Set data into recyclerview
        setUpRecyclerView()

        //Clicked button
        binding.btnPostDetailsRemitos.setOnClickListener {
            //Send the data selected
            sendDataPost()
            Log.d(TAG, "Datos enviados ${remitoAdapter.hashMap}")
            Snackbar.make(requireView(), "Datos enviados al servidor", Snackbar.LENGTH_SHORT).show()

            remitoAdapter.checkBoxStatesArray.forEach{ i: Int, b: Boolean ->
                if(b){
                    hashMap[i] = b
                }
            }
            //Here send hashMap if you need that
            Log.d(TAG, "Mine array1 $hashMap")
            hashMap.clear()
            Log.d(TAG, "Array of adapter ${remitoAdapter.checkBoxStatesArray}")
            Log.d(TAG, "Valor MYHASHMAP en RemitoFragment = ${remitoViewModel.myHashMap}")



            remitoViewModel.postRemito.observe(viewLifecycleOwner, {
                if(it.isNotEmpty()){
                    findNavController().navigate(R.id.action_remitoFragment_to_filterEpcFragment)
                    Log.d(TAG, "Livedata with @post data = ${remitoViewModel.postRemito.value}")
                }else{
                    //Feature
                }
            })

        }



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

    private fun sendDataPost(){
        remitoViewModel.sendData(remitoAdapter.hashMap)
    }

    companion object{
        const val TAG = "RemitoFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

