package com.example.lt1.data.database

import androidx.room.TypeConverter
import com.example.lt1.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter//to convert object to a string
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

}