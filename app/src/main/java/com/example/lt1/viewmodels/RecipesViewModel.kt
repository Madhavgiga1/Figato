package com.example.lt1.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.lt1.data.DataStoreRepository
import com.example.lt1.data.MealAndDietType
import com.example.lt1.util.Constants.Companion.API_KEY
import com.example.lt1.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.lt1.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.lt1.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.example.lt1.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.lt1.util.Constants.Companion.QUERY_API_KEY
import com.example.lt1.util.Constants.Companion.QUERY_DIET
import com.example.lt1.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.lt1.util.Constants.Companion.QUERY_NUMBER
import com.example.lt1.util.Constants.Companion.QUERY_SEARCH
import com.example.lt1.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private lateinit var mealAndDiet: MealAndDietType
    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveMealAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            if (this@RecipesViewModel::mealAndDiet.isInitialized) {
                dataStoreRepository.saveMealAndDietType(
                    mealAndDiet.selectedMealType,
                    mealAndDiet.selectedMealTypeId,
                    mealAndDiet.selectedDietType,
                    mealAndDiet.selectedDietTypeId
                )
            }
        }

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        mealAndDiet = MealAndDietType(
            mealType,
            mealTypeId,
            dietType,
            dietTypeId
        )
    }


    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }

}