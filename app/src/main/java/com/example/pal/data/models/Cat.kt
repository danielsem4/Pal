package com.example.pal.data.models

data class Cat(val min_weight: Int, val max_weight : Int, val breed: String,
               val min_life_expectancy : Int, val max_life_expectancy : Int,
               val grooming : Int, val children_friendly : Int, val other_pets_friendly : Int,
               val shedding: Int, val playfulness : Int, val family_friendly : Int,) {
}