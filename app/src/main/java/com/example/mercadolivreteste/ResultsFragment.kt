package com.example.mercadolivreteste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultsFragment : Fragment(R.layout.fragment_results) {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter { product ->
            productViewModel.getProductDetails(product.id)
            findNavController().navigate(R.id.productDetailFragment)
        }
        recyclerView.adapter = adapter

        productViewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products as List<com.example.mercadolivreteste.Product>)
        }
    }
}