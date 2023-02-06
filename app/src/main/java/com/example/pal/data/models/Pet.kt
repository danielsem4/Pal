package com.example.pal.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets_table")
data class Pet(val age: String, val animal: String, val breed: String, val description: String,
               val name: String, val pic: String, val sex: String) {
    @PrimaryKey(autoGenerate =true)
    var id:Int =0 //give every pet an primary key automaticlly

    constructor(): this("", "", "", "", "", "", "")
}
