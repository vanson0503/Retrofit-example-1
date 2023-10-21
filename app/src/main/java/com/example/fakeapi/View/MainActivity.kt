package com.example.fakeapi.View

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakeapi.Model.Products
import com.example.fakeapi.Model.ProductsItem
import com.example.fakeapi.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakeapi.ViewModel.StoreViewModel
import com.example.fakeapi.ViewModel.ViewModelFactory
import com.example.fakeapi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    lateinit var storeViewModel: StoreViewModel
//    private val storeViewModel: StoreViewModel by activityViewModels()
    private lateinit var storeViewModel: StoreViewModel
//    private val viewModel : StoreViewModel by viewModels()

    //    lateinit var productsList:ArrayList<ProductsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory()
        storeViewModel = ViewModelProvider(this, factory).get(StoreViewModel::class.java)
//        productsList= arrayListOf()
//        productsList.addAll(storeViewModel.getAllProducts())
//        Log.i("TESTT", "onCreate: ${storeViewModel.getAllProducts().toString()}")
//        binding.textView.setText(storeViewModel.getAllProducts().toString())

        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 3) // Quay ngang, sử dụng 3 hàng
        } else {
            GridLayoutManager(this, 2) // Mặc định, sử dụng 2 hàng
        }

        binding.rvProductList.layoutManager = layoutManager

//        binding.rvProductList.layoutManager = GridLayoutManager(this, 2)
        binding.txtAppName.visibility = View.GONE
        binding.rvProductList.visibility = View.GONE
        binding.loLoading.visibility = View.VISIBLE


        storeViewModel.getProductList().observe(this, { products ->

//            binding.textView.setText(products.toString())
            Log.i("TESTT", "onCreate: ${products.toString()}")
            binding.rvProductList.adapter = ProductAdapter(products)
            binding.rvProductList.visibility = View.VISIBLE
            binding.loLoading.visibility = View.GONE
            binding.txtAppName.visibility = View.VISIBLE
        })

//        GlobalScope.launch(Dispatchers.IO) {
//            binding.rvProductList.adapter = ProductAdapter(storeViewModel.getAllProducts())
//        }
//        storeViewModel.getProductById(25)

//        storeViewModel.getProductById(25).observe(this,{
//            product->
//            Log.i("TAG", "onCreate: ${product.toString()}")
//        })

    }
}