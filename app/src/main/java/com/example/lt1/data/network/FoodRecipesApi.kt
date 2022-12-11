package com.example.lt1.data.network

import com.example.lt1.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET(value = "/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries:Map<String,String>
    ):Response<FoodRecipe>

    //defining the interface which will pass the parameters suggested in the query map to the end oint(its a link,link ke bad jo add hoga wo)
    //abb you use retrofit to define this interface,network modeule dusre classes ko instances pass karega iss interface ke which it creates with help of retrofit library
    //network module uses retrofit to provide instance of FoodReipesApi to any class but network module ko bhi to koi retrofit ka instance de,also retrofit ko converter facory chahiye
}