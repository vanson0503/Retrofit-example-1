package com.example.fakeapi.ViewModel

import com.example.fakeapi.Model.Products
import com.example.fakeapi.Model.ProductsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("products")
    fun getAllProducts(): Call<Products>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Int): Call<ProductsItem>
}