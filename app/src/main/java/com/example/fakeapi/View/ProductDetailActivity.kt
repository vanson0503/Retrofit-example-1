package com.example.fakeapi.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.fakeapi.R
import com.example.fakeapi.ViewModel.StoreViewModel
import com.example.fakeapi.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private val storeViewModel : StoreViewModel by viewModels()
    lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        storeViewModel.getProductById(intent.getIntExtra("id",0)).observe(this,{
            product->
            setUpToolbar(product.title)
            val adapter = ImageAdapter(product.images,binding.vp2Image)
            binding.vp2Image.adapter = adapter
        })



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Xử lý khi người dùng ấn vào nút quay lại
                finish() // Đóng Activity hiện tại và quay lại Activity trước đó
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun setUpToolbar(title:String){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Hiển thị nút quay lại
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}