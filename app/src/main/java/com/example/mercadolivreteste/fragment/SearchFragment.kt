package com.example.mercadolivreteste.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mercadolivreteste.viewmodel.ProductViewModel
import com.example.mercadolivreteste.R
import com.example.mercadolivreteste.SearchActivity

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<TextView>(R.id.searchViewFake)

        searchView.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivityForResult(intent, 1)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                val query = data?.getStringExtra("query")
                if (!query.isNullOrEmpty()) {
                    val action = SearchFragmentDirections.actionSearchFragmentToResultsFragment(query)
                    findNavController().navigate(action)
                }
            }
        }

        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            productViewModel.searchProducts(query)
            findNavController().navigate(R.id.action_searchFragment_to_resultsFragment)
        }
    }
}
