package com.example.pal.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "pets_tablee")
data class Pet(val age: String,
               val animal: String,
               val breed: String,
               val description: String,
               val name: String,
               val pic: String,
               val sex: String,
               @PrimaryKey
                val id:Int) {
   // @PrimaryKey(autoGenerate =true)
    //var id:Int =0 //give every pet an primary key automaticlly

    constructor(): this("", "", "", "", "", "", "",0)//why
}


/*
@Entity(tableName = "petsss_table", primaryKeys = ["age", "breed", "name"])
data class Pet(
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "animal") val animal: String,
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "pic") val pic: String,
    @ColumnInfo(name = "sex") val sex: String
)

 */