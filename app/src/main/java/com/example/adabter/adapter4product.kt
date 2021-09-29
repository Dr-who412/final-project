package com.example.adabter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dat.ProductData
import com.example.dat.RestaurantListData
import com.example.umum.R
import com.example.umum.listOfRestaurantDirections

class CustomAdapterProduct(var data:List<ProductData>, var productsFragment: View) : RecyclerView.Adapter<CustomAdapterProduct.ViewHolder>(){
    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.prod_name)
        val imagepro : ImageView = itemView.findViewById(R.id.prodimage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brod_row,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    var list = data[position]
    holder.name.text = list.name
    Glide.with(productsFragment).load(list.image).into(holder.imagepro)
    }
}