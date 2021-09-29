package com.example.umum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adabter.CustomAdapter
import com.example.dat.RestaurantListData
import com.example.navcomp.MyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [listOfRestaurant.newInstance] factory method to
 * create an instance of this fragment.
 */
class listOfRestaurant : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var recyclerView: RecyclerView
    lateinit var cardView: CardView
    lateinit var adapter: CustomAdapter
    lateinit var nav: Navigation
    var myViewModel : MyViewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeView(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_of_restaurant, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listOfRestaurant.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            listOfRestaurant().apply {
                arguments = Bundle().apply {
                }
            }
    }
    fun makeView(view: View) {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getData()
        myViewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {
            recyclerView = view.findViewById(R.id.recyclerViewRest)
            adapter = CustomAdapter(it as ArrayList<RestaurantListData>,view)
            recyclerView.layoutManager  = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        })

        myViewModel.error_message.observe(viewLifecycleOwner , Observer {
            Toast.makeText(view.context , it, Toast.LENGTH_SHORT).show()

        })
    }
}