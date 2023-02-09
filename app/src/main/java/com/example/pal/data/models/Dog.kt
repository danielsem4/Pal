package com.example.pal.data.models

data class Dog(val Apartment_Living: Int, val Barking_Level: Int, val Breed: String,
               val Drooling_Level: Int, val Easy_To_Train: Int, val Intelligence: Int,
               val Kid_Friendly: Int, val Life_Span: String,
               val Other_Dogs_Friendly: Int, val Shedding: Int, val Strangers_Friendly: Int,
               val Size: Int, val Tolerates_Being_Alone: Int, val Weight: String, val image: String)
    : java.io.Serializable {

    constructor(): this(0, 0, "", 0, 0, 0, 0, "", 0, 0, 0,
        0, 0, "", "")

}