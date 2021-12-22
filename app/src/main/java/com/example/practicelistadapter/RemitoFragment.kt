package com.example.practicelistadapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicelistadapter.data.Remito
import com.example.practicelistadapter.data.RemitoResponse
import com.example.practicelistadapter.databinding.FragmentRemitoBinding
import com.example.practicelistadapter.databinding.ItemRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemitoFragment : Fragment(){

    private val remitoViewModel: RemitoViewModel by viewModels()
    private lateinit var binding: FragmentRemitoBinding
    private lateinit var itemRemitoBinding : ItemRecyclerviewBinding
    private lateinit var remitoAdapter: RemitoAdapter


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
        })
    }



}