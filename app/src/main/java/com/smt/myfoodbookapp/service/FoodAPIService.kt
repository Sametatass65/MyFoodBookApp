package com.smt.myfoodbookapp.service

import com.smt.myfoodbookapp.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIService {

    val BASE_URL ="https://raw.githubusercontent.com"

    private val api = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build().create(FoodAPI :: class.java)
    fun GetData(): Single<List<Food>>{
        return api.GetFood()
    }
}