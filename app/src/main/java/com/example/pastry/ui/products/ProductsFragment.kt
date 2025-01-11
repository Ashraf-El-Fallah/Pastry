package com.example.pastry.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pasrty.R
import com.example.pasrty.databinding.FragmentHomeBinding
import com.example.pasrty.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }
}