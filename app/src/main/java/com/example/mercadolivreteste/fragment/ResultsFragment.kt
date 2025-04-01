package com.example.mercadolivreteste.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolivreteste.Product
import com.example.mercadolivreteste.ProductAdapter
import com.example.mercadolivreteste.viewmodel.ProductViewModel
import com.example.mercadolivreteste.R

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
            adapter.submitList(products as List<Product>)
        }
    }
}