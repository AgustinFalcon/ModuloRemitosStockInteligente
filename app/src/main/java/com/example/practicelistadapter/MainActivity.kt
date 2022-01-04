package com.example.practicelistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicelistadapter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //For the bar in every screen
        supportActionBar?.hide()
        setContentView(binding.root)

    }


}