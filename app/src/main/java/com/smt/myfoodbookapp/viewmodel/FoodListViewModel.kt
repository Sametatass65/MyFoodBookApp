package com.smt.myfoodbookapp.viewmodel

import android.app.Application
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smt.myfoodbookapp.model.Food
import com.smt.myfoodbookapp.service.FoodAPIService
import com.smt.myfoodbookapp.service.FoodDataBase
import com.smt.myfoodbookapp.util.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) :BaseViewModel(application){

    val foods = MutableLiveData<List<Food>>()
    val foodDownloadMessage= MutableLiveData<Boolean>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L

    private val foodApiService = FoodAPIService()
    private val disposiable = CompositeDisposable()
    private val ozelSharedPreferences = SpecialSharedPreferences(getApplication())


    fun refredhData(){

        val saveTime = ozelSharedPreferences.GetTime()

        if(saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime){
            // sqlite'ten çek
            GetDataFromSQLite()

        }else{
            GetDataFromInternet()
        }

    }
    fun GetDataFromSQLite(){
        launch {
            val foodList = FoodDataBase(getApplication()).foodDAO().getAllFood()
            FoodShow(foodList)
        }
    }

    fun GetDataFromInternet(){
        foodDownloadMessage.value = true
        disposiable.add(
            foodApiService.GetData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {
                       storageDataInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodDownloadMessage.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
    fun FoodShow(foodList : List<Food>){
        foods.value = foodList
        foodErrorMessage.value = false
        foodDownloadMessage.value = false
    }

    fun storageDataInSQLite(foodList: List<Food>){
        launch {
            val dao = FoodDataBase(getApplication()).foodDAO()
            dao.deletedFood()

            val uuidList = dao.insertAll(*foodList.toTypedArray())

            var i = 0
            while(i < foodList.size){
                foodList[i].uuid = uuidList[i].toInt()
                i = i+1
            }
            FoodShow(foodList)
        }
    }
}





























