package com.example.pal.data.models

data class Pet(val age: String, val animal: String, val breed: String, val description: String,
               val name: String, val pic: String, val sex: String) {
    constructor(): this("", "", "", "", "", "", "")
}
