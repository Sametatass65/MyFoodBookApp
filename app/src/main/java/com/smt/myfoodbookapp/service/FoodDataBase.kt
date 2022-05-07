package com.smt.myfoodbookapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smt.myfoodbookapp.model.Food

@Database(entities = arrayOf(Food::class), version = 1)
 abstract  class FoodDataBase:RoomDatabase() {
     abstract fun foodDAO() :FoodDao

     companion object{
         @Volatile private var instance : FoodDataBase? = null

         private val lock = Any()
         operator fun invoke(context: Context) = instance ?: synchronized(lock){
             instance ?: MakeDatabase(context).also{
                 instance = it
             }
         }

         private fun MakeDatabase(context: Context) = Room.databaseBuilder(
             context.applicationContext,
             FoodDataBase::class.java,
             "fooddatabase").build()
     }
}
// entşty oluturduk - dao oluşturduk - database oluşturduk