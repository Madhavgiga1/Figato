package com.example.lt1.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lt1.util.Constants.Companion.RECIPES_TABLE

@Entity( tableName = RECIPES_TABLE)
class RecipesEntity {
    @PrimaryKey(autoGenerate = false)//when you fetch data from API you are ging to replace all data with new data
    var id:Int=0

}