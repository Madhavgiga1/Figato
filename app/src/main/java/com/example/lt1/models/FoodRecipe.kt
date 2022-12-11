package com.example.lt1.models


import com.example.lt1.models.Result
import com.google.gson.annotations.SerializedName

data class FoodRecipe(

    @SerializedName("results")
    val results: List<Result>
)