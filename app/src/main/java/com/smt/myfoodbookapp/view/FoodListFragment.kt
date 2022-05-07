package com.smt.myfoodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.smt.myfoodbookapp.R
import com.smt.myfoodbookapp.adapter.RecyclerViewAdapter
import com.smt.myfoodbookapp.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodListFragment : Fragment() {

   private lateinit var viewModel : FoodListViewModel
   private var recyclerViewAdapter = RecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // fragmentlarda view islemlerini OnViewCreatedın içinde yapıyoruz

        // viewModelı getirelim
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel :: class.java)
        viewModel.refredhData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        swipeRefreshLayout.setOnRefreshListener { // refreshlayoutun çalışmasını düzenliyoruz
            // verileri yenilemek istiyoruz
            foodDownload.visibility = View.VISIBLE
            foodErrorMessage.visibility = View.GONE
            recyclerView.visibility = View.GONE
            viewModel.refredhData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer { besin->
            besin?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerViewAdapter.FoodListUpdate(it)
            }
        })
        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    foodErrorMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    foodDownload.visibility = View.GONE
                }else{
                    foodErrorMessage.visibility = View.GONE
                }
            }
        })
        viewModel.foodDownloadMessage.observe(viewLifecycleOwner, Observer {
            if(it){
                foodDownload.visibility = View.VISIBLE
            }else{
                foodDownload.visibility = View.GONE
            }
        })
    }
}






















































