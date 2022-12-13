package com.example.lt1.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lt1.models.FoodRecipe
import com.example.lt1.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}