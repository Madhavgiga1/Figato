package com.example.lt1.data

import com.example.lt1.data.database.RecipesDao
import com.example.lt1.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao){

    fun readDatabase():Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }


    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }
}