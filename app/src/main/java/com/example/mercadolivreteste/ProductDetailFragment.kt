package com.example.mercadolivreteste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import coil.Coil
import coil.load

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productTitle: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView
    private lateinit var productImage: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        productTitle = view.findViewById(R.id.productDetailName)
        productPrice = view.findViewById(R.id.productDetailPrice)
        productDescription = view.findViewById(R.id.productDetailDescription)
        productImage = view.findViewById(R.id.productDetailImage)

        productViewModel.productDetail.observe(viewLifecycleOwner) { productDetail ->
            productTitle.text = productDetail.title
            productPrice.text = "R$ ${productDetail.price}"
            productDescription.text = productDetail.description
            val imageUrl = productDetail.pictures.firstOrNull()?.url
            if (imageUrl != null) {
                productImage.load(imageUrl)

            }
        }
    }
}
