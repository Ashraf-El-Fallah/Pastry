package com.example.pastry.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pasrty.R
import com.example.pasrty.databinding.FragmentHomeBinding
import com.example.pasrty.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShoppingBinding.inflate(layoutInflater)
        return binding.root
    }
}