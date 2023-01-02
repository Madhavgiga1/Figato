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

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Response<FoodRecipe>
    // there are lots of restful api(sports,news api) basically webservices jinke pass kuch data expose karne ko hota h and when we send http requests to them wo data send karti h JSON format me

    /* consider an API that allows clients to search for books by title. The API might define a method in its interface called searchBooks() that takes a query map as a parameter and returns
     a list of books. The query map might contain a key called title that represents the title of the book to search for. To search for books with the title "The Great Gatsby", the client 
     could call the searchBooks() method and pass a query map with the title key set to "The Great Gatsby". The API would then use the value of the title key to search for books
     with the specified title and return the results to the client.*/


    //defining the interface which will pass the parameters suggested in the query map to the end oint(its a link,link ke bad jo add hoga wo)
    //abb you use retrofit to define this interface,network module dusre classes ko instances pass karega iss interface ke which it creates with help of retrofit library
    //network module uses retrofit to provide instance of FoodReipesApi to any class but network module ko bhi to koi retrofit ka instance de,also retrofit ko converter facory chahiye
}