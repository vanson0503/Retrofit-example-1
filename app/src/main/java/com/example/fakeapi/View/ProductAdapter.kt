package com.example.fakeapi.View

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakeapi.Model.Products
import com.example.fakeapi.R

class ProductAdapter(val products: Products) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val productTitle = itemView.findViewById<TextView>(R.id.tvProductTitle)
        val productPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val productItem = itemView.findViewById<LinearLayout>(R.id.productItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.apply {
            Glide
                .with(productImage)
                .load(product.images[0])
                .into(productImage)
            productTitle.setText(product.title)
            productPrice.setText("$${product.price}")

            productItem.setOnClickListener{
                var intent = Intent(itemView.context,ProductDetailActivity::class.java).apply {
                    putExtra("id",product.id)
                }
                itemView.context.startActivity(intent)
            }
        }
    }


}