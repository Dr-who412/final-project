package com.example.umum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adabter.CustomAdapter
import com.example.adabter.CustomAdapterProduct
import com.example.navcomp.MyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [productOfrest.newInstance] factory method to
 * create an instance of this fragment.
 */
class productOfrest : Fragment() {
    lateinit var name2: TextView
    lateinit var address2: TextView
    lateinit var Image2: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var cardView: CardView
    lateinit var adapter: CustomAdapterProduct
    lateinit var nav: Navigation
    var myViewModel : MyViewModel = MyViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id= arguments?.getString("id")
        val name= arguments?.getString("name")
        val address=arguments?.getString("address")
        val image=arguments?.getString("image")
        if(name!=null&&image!=null&&address!=null)
            show(view,name, address,image)
        makeView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_ofrest, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment productOfrest.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            productOfrest().apply {
                arguments = Bundle().apply {
                }
            }
    }
    @SuppressLint("SetTextI18n")
    fun show(view: View, name:String, address:String, image:String){
        name2 = view.findViewById(R.id.restnameprod)
        address2 = view.findViewById(R.id.addresspro)
        Image2 = view.findViewById(R.id.imageprodRest)
        recyclerView = view.findViewById(R.id.prod_lest)
        name2.text = " $name"
        address2.text ="$address"
        Glide.with(view.context).load(image).into(Image2)
    }
    fun makeView(view: View) {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getData()
        myViewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {

            var product=it[id].products
            adapter = CustomAdapterProduct(product,view)
            recyclerView.layoutManager  = GridLayoutManager(view.context,2)
            recyclerView.adapter = adapter
        })

        myViewModel.error_message.observe(viewLifecycleOwner , Observer {
            Toast.makeText(view.context , it, Toast.LENGTH_SHORT).show()

        })
    }
}