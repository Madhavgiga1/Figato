package com.example.lt1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lt1.databinding.RecipesRowLayoutBinding
import com.example.lt1.models.FoodRecipe
import com.example.lt1.models.Result
import com.example.lt1.util.RecipesDiffUtil

class RecipesAdapter():RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {
    private var recipes = emptyList<Result>()

    /*in Recipe list you have collection of recipes which have all the parameer that are there in result model calss which we get from API.Recycler view displays  collection of item where each
    item has a layout specified.When it needs to display an item Recycler View tells adapter to create a ViewHolder which will hold parent element of items layout and also bind data from collection
    of recipes to the viewholder.Recipes is a list of recipes,in the on bindview holder we pas sthe current recipe as a parameter to the Myviewholder classes bind function,
    tune jo bhi layout liya h uske data binding layout me tune variable mention kiya h Result model apan wo hi parameter pass kar rhe h
    */
    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(result: Result){
                binding.result=result
                binding.executePendingBindings()
            }
            companion object{
                fun from(parent: ViewGroup):MyViewHolder{
                    val layoutInflater=LayoutInflater.from(parent.context)
                    val binding=RecipesRowLayoutBinding.inflate(layoutInflater,parent,false)
                    return MyViewHolder(binding)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: FoodRecipe){
        val recipesDiffUtil =
            RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}