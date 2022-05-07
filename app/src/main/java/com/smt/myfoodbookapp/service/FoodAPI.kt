package com.smt.myfoodbookapp.service

import com.smt.myfoodbookapp.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun GetFood() : Single<List<Food>>
}