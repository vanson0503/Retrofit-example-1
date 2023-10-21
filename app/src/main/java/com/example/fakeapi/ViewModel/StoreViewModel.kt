package com.example.fakeapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakeapi.Model.Products
import com.example.fakeapi.Model.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreViewModel : ViewModel() {
    private var storeLiveData: LiveData<Products>? = null

    fun getProductById(id:Int):LiveData<ProductsItem>{
        var product = MutableLiveData<ProductsItem>()
        RetrofitInstance.api.getProductById(id)
            .enqueue(object : Callback<ProductsItem>{
                override fun onResponse(
                    call: Call<ProductsItem>,
                    response: Response<ProductsItem>
                ) {
                    if(response.isSuccessful){
                        product.value = response.body()
                        Log.d("TAG", "Response not successful ${product.toString()}")
                    }
                    else {
                        // Xử lý lỗi khi response không thành công
                        Log.d("TAG", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                    // Xử lý lỗi khi gọi API thất bại
                    Log.d("TAG", t.message.toString())
                }

            })
        return product
    }

    fun getProductList(): LiveData<Products> {
        if (storeLiveData == null) {
            storeLiveData = loadData()
        }
        return storeLiveData!!
    }

    private fun loadData(): LiveData<Products> {
        val mutableLiveData = MutableLiveData<Products>()
        RetrofitInstance.api.getAllProducts()
            .enqueue(object : Callback<Products> {
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    if (response.isSuccessful) {
                        mutableLiveData.value = response.body()
                        Log.d("TAGGG", mutableLiveData.value.toString())
                    } else {
                        // Xử lý lỗi khi response không thành công
                        Log.d("TAG", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    // Xử lý lỗi khi gọi API thất bại
                    Log.d("TAG", t.message.toString())
                }
            })
        return mutableLiveData
    }
}





//// Create a MutableLiveData object to
//// hold the list of GitHub repositories
//private var githubLiveData = MutableLiveData<List<Item>>()
//// Fetch the list of Kotlin repositories
//// from the GitHub API using Retrofit
//fun getAllKotlinRepos(){
//    RetrofitInstance.api.getKotlinRepos("language:kotlin",">2022-12-19")
//        .enqueue(object : Callback<Repo> {
//            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
//                // If the API call is successful and the response body is not null,
//                // set the value of githubLiveData to the list of repositories returned by the API
//                if (response.body() != null){
//                    githubLiveData.value = response.body()!!.items
//                } else {
//                    return
//                }
//            }
//
//            // If the API call fails, log the error message using Logcat
//            override fun onFailure(call: Call<Repo>, t: Throwable) {
//                Log.d("TAG",t.message.toString())
//            }
//        })
//}
//// Expose the list of repositories as LiveData
//// so it can be observed by the UI
//fun observeGitHubLiveData() : LiveData<List<Item>> {
//    return githubLiveData
//}