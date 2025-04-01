package com.example.mercadolivreteste

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _productDetail = MutableLiveData<ProductDetail>()
    val productDetail: LiveData<ProductDetail> get() = _productDetail

    fun searchProducts(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchProducts(query)
                if (response.isSuccessful) {
                    _products.value = response.body()?.results
                } else {
                    Log.e("ProductViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Exception: ${e.localizedMessage}")
            }
        }
    }

    fun getProductDetails(itemId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductDetails(itemId)
                if (response.isSuccessful) {
                    _productDetail.value = response.body()
                } else {
                    Log.e("ProductViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Exception: ${e.localizedMessage}")
            }
        }
    }
}

