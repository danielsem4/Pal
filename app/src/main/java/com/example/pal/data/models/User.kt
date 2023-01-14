package com.example.pal.data.models

data class User(val name: String = "",val email: String = "",val phone: String? = "",
                val favorites: ArrayList<String>? = ArrayList())