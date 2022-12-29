package com.example.lt1.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lt1.models.Result


@Entity(tableName = com.example.lt1.util.Constants.FAVORITE_RECIPES_TABLE)
class FavoritesEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var result: Result
    ){

}