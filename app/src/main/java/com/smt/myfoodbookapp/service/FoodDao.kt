package com.smt.myfoodbookapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smt.myfoodbookapp.model.Food

@Dao
interface FoodDao {


    @Insert
    suspend fun insertAll(vararg food : Food):List<Long> // tüm besinleri veri tabanına ekliyor

    @Query("Select * From Food") // veritabanında olan food tablosunun tamamını alıyor
    suspend fun getAllFood() : List<Food>

    @Query("Select * From Food Where uuid = :foodId")  // ürünleri idye göre alıyor
    suspend fun GetByFoodId(foodId : Int):Food

    @Query("Delete From Food") // bütün food tablosunu siliyor
    suspend fun deletedFood()
    // enity oluştu dao oluştu databasei oluşyuucaz


}