package com.smt.myfoodbookapp.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SpecialSharedPreferences {

    companion object{
        private val Time = "time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance :SpecialSharedPreferences?= null
        private val lock = Any()
        operator fun invoke(context: Context): SpecialSharedPreferences = instance ?: synchronized(lock){
            instance ?: MakeSpecialSharedPreference(context).also{
                instance = it
            }
        }
        private fun MakeSpecialSharedPreference(context: Context): SpecialSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPreferences()
        }
    }

    fun SaveTime(time : Long){
        sharedPreferences?.edit(commit = true){
            putLong(Time,time)
        }
    }
    fun GetTime()= sharedPreferences?.getLong(Time,0)

}