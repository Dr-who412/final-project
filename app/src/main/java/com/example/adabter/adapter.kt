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
import com.example.dat.RestaurantListData
import com.example.umum.R
import com.example.umum.listOfRestaurantDirections

class CustomAdapter(var data:ArrayList<RestaurantListData>, var restFragment: View) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.restnameprod)
        val address : TextView = itemView.findViewById(R.id.addresspro)
        val image : ImageView = itemView.findViewById(R.id.prodimage)
        val directionbutton:ImageView=itemView.findViewById(R.id.imageButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var list = data[position]

        holder.name.text = "${list.name}"
        holder.address.text = "  ${list.address}"
        Glide.with(restFragment).load(list.image).into(holder.image)
        holder.image.setOnClickListener {
            var action =listOfRestaurantDirections.actionListOfRestaurantToProductOfrest(list.id)
             Navigation.findNavController(restFragment).navigate(action)
        }
        holder.directionbutton.setOnClickListener {
            var action =listOfRestaurantDirections.actionListOfRestaurantToMaps(list.name,list.restaurant_lat,list.restaurant_long)
            Navigation.findNavController(restFragment).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}