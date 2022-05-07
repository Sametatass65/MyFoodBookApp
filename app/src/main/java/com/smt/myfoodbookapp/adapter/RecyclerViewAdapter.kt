package com.smt.myfoodbookapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.smt.myfoodbookapp.R
import com.smt.myfoodbookapp.model.Food
import com.smt.myfoodbookapp.util.PlaceHolderMake
import com.smt.myfoodbookapp.util.imageDownload
import com.smt.myfoodbookapp.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_view_row.view.*

class RecyclerViewAdapter(var foodList: ArrayList<Food> ): RecyclerView.Adapter<RecyclerViewAdapter.FoodVH>() {
    class FoodVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_recycler_view_row,parent,false)
        return  FoodVH(view)
    }

    override fun onBindViewHolder(holder: FoodVH, position: Int) {
        holder.itemView.foodNameText.text = foodList[position].foodName
        holder.itemView.foodCalorieText.text = foodList[position].foodCalorie
        // resim eklenicek glide ile
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.imageDownload(foodList.get(position).foodImage, PlaceHolderMake(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun FoodListUpdate(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}