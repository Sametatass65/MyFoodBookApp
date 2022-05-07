package com.smt.myfoodbookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.smt.myfoodbookapp.R
import com.smt.myfoodbookapp.util.PlaceHolderMake
import com.smt.myfoodbookapp.util.imageDownload
import com.smt.myfoodbookapp.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_food_detail.*

class FoodDetailFragment : Fragment() {

    private lateinit var viewModel : FoodDetailViewModel
    private var besinId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
             besinId = FoodDetailFragmentArgs.fromBundle(it).besinId
        }


        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel :: class.java)
        viewModel.SendData(besinId)
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { besin->
            besin?.let {
                foodText.text = it.foodName
                calorieText.text = it.foodCalorie
                proteinText.text = it.foodProtein
                karbonhidratText.text = it.foodKarbonhidrat
                yagText.text = it.foodOil
                context?.let {
                    foodImageView.imageDownload(besin.foodImage, PlaceHolderMake(it))
                }

            }
        })
    }
}












































