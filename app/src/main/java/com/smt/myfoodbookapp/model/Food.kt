package com.smt.myfoodbookapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
class Food( @ColumnInfo(name = "isim")
            @SerializedName("isim")
            val foodName:String?,
            @ColumnInfo(name = "kalori")
            @SerializedName("kalori")
            val foodCalorie :String?,
            @ColumnInfo(name = "karbonhidrat")
            @SerializedName("karbonhidrat")
            val foodKarbonhidrat:String?,
            @ColumnInfo(name = "protein")
            @SerializedName("protein")
            val foodProtein:String?,
            @ColumnInfo(name = "yag")
            @SerializedName("yag")
            val foodOil:String?,
            @ColumnInfo(name = "gorsel")
            @SerializedName("gorsel")
            val foodImage : String) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}

//amacımız internetten çektiğimiz verileri Sql lite kaydetmek yani data base oluşturmak
// ilk entiity oluşturcaz sonra Dao sonra ise dateBasei oluşturucaz
// sonra verleri data baseden çekmeye başlıyacaz






















