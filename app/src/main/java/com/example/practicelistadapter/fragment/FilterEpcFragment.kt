package com.example.practicelistadapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicelistadapter.adapter.FilterEpcAdapter
import com.example.practicelistadapter.databinding.FragmentFilterEpcBinding
import com.example.practicelistadapter.viewmodel.RemitoViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterEpcFragment : Fragment() {

    private lateinit var filterEpcAdapter: FilterEpcAdapter

    private var _binding: FragmentFilterEpcBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val remitoViewModel:RemitoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilterEpcBinding.inflate(layoutInflater, container, false)


        remitoViewModel.postRemito.observe(viewLifecycleOwner, {
            setUpRecyclerView()
        })


        return binding.root
    }

    private fun setUpRecyclerView(){
        filterEpcAdapter = FilterEpcAdapter(remitoViewModel.myHashMap)
        binding.rvFilterEpc.apply {
            adapter = filterEpcAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        Log.d(TAG, "Valor del remitoHashMap = ${remitoViewModel.myHashMap}")
    }


    companion object {
        const val TAG = "FilterEpcFragment"
    }

}