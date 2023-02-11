package com.example.pal.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    val name: String = "",
    @PrimaryKey
    val email: String = "",
    val phone: String? = "",
    val favorites: ArrayList<String>? = ArrayList()
)