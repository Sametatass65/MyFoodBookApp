package com.smt.myfoodbookapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smt.myfoodbookapp.model.Food
import com.smt.myfoodbookapp.service.FoodDataBase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application):BaseViewModel(application) {

    val foodLiveData = MutableLiveData<Food>()

    fun SendData (uuid : Int){
        launch {
            val dao = FoodDataBase(getApplication()).foodDAO()
            val food = dao.GetByFoodId(uuid)
            foodLiveData.value = food
        }
    }
}