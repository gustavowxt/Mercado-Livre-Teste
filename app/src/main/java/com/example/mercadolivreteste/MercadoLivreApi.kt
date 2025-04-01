package com.example.mercadolivreteste


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response


interface MercadoLivreApi {
    @GET("sites/MLB/search")
    suspend fun searchProducts(@Query("q") query: String): Response<ProductSearchResponse>

    @GET("items/{item_id}")
    suspend fun getProductDetails(@Path("item_id") itemId: String): Response<ProductDetail>
}
